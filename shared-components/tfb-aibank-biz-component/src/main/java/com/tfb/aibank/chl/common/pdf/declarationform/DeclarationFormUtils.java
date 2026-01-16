package com.tfb.aibank.chl.common.pdf.declarationform;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.chl.common.pdf.declarationform.model.DeclarationForm;
import com.tfb.aibank.chl.common.pdf.declarationform.model.DeclarationForm.DeclarationPageEnum;
import com.tfb.aibank.chl.common.pdf.declarationform.model.DeclarationFormEnum;
import com.tfb.aibank.chl.common.pdf.declarationform.model.IDeclarationFormEnum;

public class DeclarationFormUtils {

    protected static IBLog logger = IBLog.getLog(DeclarationFormUtils.class);

    // 外匯申報性質別
    private static final Integer REMIT_TYPE_1_AND_2 = 1;
    private static final Integer REMIT_TYPE_3 = 3;

    /**
     * 將申報書PDF附件轉為Base64字串
     * 
     * @param declarationForm
     * @return
     * @throws ActionException
     */
    public static String getPdfBase64String(DeclarationForm declarationForm, String password, Class<?> obj) throws ActionException {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // 將查詢出來的資料塞到PDF中
            writePdf(outputStream, declarationForm, password, obj);
            byte[] byteArray = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        }
        catch (IOException e) {
            logger.error("產製外匯申報書時發生錯誤", e);
            throw ExceptionUtils.getActionException(e);
        }
    }

    public static byte[] getPdfBase64(DeclarationForm declarationForm, String password, Class<?> obj) throws ActionException {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // 將查詢出來的資料塞到PDF中
            writePdf(outputStream, declarationForm, password, obj);
            return outputStream.toByteArray();
        }
        catch (IOException e) {
            logger.error("產製外匯申報書時發生錯誤", e);
            throw ExceptionUtils.getActionException(e);
        }
    }

    /**
     * 寫入申報書資料
     *
     * @param outputStream
     * @throws IOException
     * @throws MessagingException
     */
    private static void writePdf(ByteArrayOutputStream outputStream, DeclarationForm declarationForm, String password, Class<?> obj) throws IOException {

        // PDF編輯
        PDDocument document = writePdfContent(declarationForm, obj);
        if (StringUtils.isNotBlank(password)) {
            // 設定權限，允許列印但不允許修改
            AccessPermission accessPermission = new AccessPermission();
            accessPermission.setCanPrint(true);
            accessPermission.setCanModify(false);
            // 使用擁有者密碼和使用者密碼進行加密
            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(password, password, accessPermission);
            // 設置加密強度為128位
            protectionPolicy.setEncryptionKeyLength(128);
            // 防止文件在未解密時顯示元數據
            protectionPolicy.setPreferAES(true);
            // 對 PDF 文件進行加密
            document.protect(protectionPolicy);
        }

        document.save(outputStream);
        document.close();
    }

    public static <E extends Enum<E> & IDeclarationFormEnum> PDDocument writePdfContent(DeclarationForm declarationForm, Class<?> obj) throws IOException {

        Class<E> enumType = getDeclarationFormEnum(declarationForm.getDocVer(), declarationForm.getPage());
        String pdfPath = "pdf/declarationform/DeclarationForm_" + declarationForm.getSettlement() + "_" + declarationForm.getDocVer() + "_" + declarationForm.getPage() + ".pdf";
        logger.info("DeclarationForm PDF Template url =>" + pdfPath);
        ClassLoader cl = obj.getClassLoader();
        if (cl == null) {
            logger.error("error getting class loader");
            return new PDDocument();
        }

        PDDocument document = null;
        try (InputStream pdfTmpStream = cl.getResourceAsStream(pdfPath);) {
            byte[] pdfTmpbyte = convertInputStreamToByteArray(pdfTmpStream);
            // 需附件帶上申報書附件
            document = Loader.loadPDF(pdfTmpbyte);
        }
        if (document == null) {
            logger.error("pdf loader error");
            return new PDDocument();
        }

        // 取得表單頁面
        PDPage page = document.getPage(0);
        PDType0Font font = null;
        // 內容流
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);) {
            try (InputStream fontUrlStream = cl.getResourceAsStream("config/fonts/kai.ttf");) {
                // 取得中文字體
                font = PDType0Font.load(document, fontUrlStream);
            }

            if (font == null) {
                logger.error("font loader error");
                return new PDDocument();
            }
            if (DeclarationFormUtils.hasGlyph(declarationForm, font)) {
                // 設定字體
                contentStream.setFont(font, 12);
            }
            else {
                try (InputStream fontUrlStream = cl.getResourceAsStream("config/fonts/NotoSansCJKtc-Regular.ttf");) {
                    // 取得中文字體
                    font = PDType0Font.load(document, fontUrlStream);
                }
                contentStream.setFont(font, 10);
            }

            SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
            SimpleDateFormat getMonthFormat = new SimpleDateFormat("MM");
            SimpleDateFormat getDayFormat = new SimpleDateFormat("dd");

            /*
             * 付/匯款方式為現鈔旅支【TOP欄位=C】，交易類別為(4) TOP欄位非C，受款地區國別非TW時，交易類別為(1) TOP欄位非C，受款地區國別TW時，匯款性質=692，交易類別為(3) TOP欄位非C，受款地區國別TW時，匯款性質=693，交易類別為(2) TOP欄位非C，受款地區國別TW時，匯款性質=695，交易類別為(5)
             */
            IDeclarationFormEnum topChecked = Enum.valueOf(enumType, DeclarationFormEnum.CHECK_OPTION_3.name());
            if ("C".equals(declarationForm.getTop())) {
                topChecked = Enum.valueOf(enumType, DeclarationFormEnum.CHECK_OPTION_4.name());
            }
            else {
                if (!"TW".equals(declarationForm.getCountries())) {
                    topChecked = Enum.valueOf(enumType, DeclarationFormEnum.CHECK_OPTION_1.name());
                }
                else {
                    if (declarationForm.getRemittance() != null) {
                        String remittance = StringUtils.EMPTY;
                        // REMITTANCE 外匯收入或交易性質[說明--大類--小類--外匯性質別]
                        if (declarationForm.getRemittance().indexOf("--") > 0) {
                            String[] remittanceArr = declarationForm.getRemittance().split("--");
                            remittance = remittanceArr[1];
                        }
                        else {
                            // 無記錄到中文說明
                            remittance = declarationForm.getRemittance();
                        }
                        if ("692".equals(remittance) || "692".equals(declarationForm.getCategory())) {
                            topChecked = Enum.valueOf(enumType, DeclarationFormEnum.CHECK_OPTION_3.name());
                        }
                        else if ("693".equals(remittance) || "693".equals(declarationForm.getCategory())) {
                            topChecked = Enum.valueOf(enumType, DeclarationFormEnum.CHECK_OPTION_2.name());
                        }
                        else if ("695".equals(remittance) || "695".equals(declarationForm.getCategory())) {
                            topChecked = Enum.valueOf(enumType, DeclarationFormEnum.CHECK_OPTION_5.name());
                        }
                    }
                }
            }

            // 1. 由外匯存款提出結售 || 結購外匯存入外匯存款
            float x = topChecked.getX(declarationForm.getSettlement()); // x 座標
            float y = topChecked.getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText("V");
            contentStream.endText();

            // 2. 申報日期(年)
            x = Enum.valueOf(enumType, DeclarationFormEnum.APPLY_DATE_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.APPLY_DATE_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            String txDateYear = getYearFormat.format(declarationForm.getTxDate());
            contentStream.showText(txDateYear);
            contentStream.endText();

            // 3. 申報日期(月)
            x = Enum.valueOf(enumType, DeclarationFormEnum.APPLY_DATE_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.APPLY_DATE_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            String txDateMonth = getMonthFormat.format(declarationForm.getTxDate());
            contentStream.showText(txDateMonth);
            contentStream.endText();

            // 4. 申報日期(日)
            x = Enum.valueOf(enumType, DeclarationFormEnum.APPLY_DATE_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.APPLY_DATE_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            String txDateDate = getDayFormat.format(declarationForm.getTxDate());
            contentStream.showText(txDateDate);
            contentStream.endText();

            // 5. 申報義務人
            x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_NAME.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_NAME.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            String name = declarationForm.getName();
            contentStream.showText(name);
            contentStream.endText();

            // 判斷居留證是否來還有一年效期
            Date currentDate = new Date();
            DateUtils.clearTime(currentDate);
            Date oneYearLater = DateUtils.addDay(currentDate, 364);// 超過364天為滿一年

            // 6. 判斷身份別
            if ("1".equals(declarationForm.getIdType())) {
                // 公司、有限合夥、行號
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_1.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_1.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("V");
                contentStream.endText();

                // 公司、有限合夥、行號 - 統一編號
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_1_UNIFORM_NO.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_1_UNIFORM_NO.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String idNo = declarationForm.getIdNo();
                contentStream.showText(idNo);
                contentStream.endText();
            }
            else if ("2".equals(declarationForm.getIdType())) {
                // 團體、辦事處及事務所
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_2.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_2.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("V");
                contentStream.endText();

                // 團體、辦事處及事務所 - 統一編號
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_2_UNIFORM_NO.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_2_UNIFORM_NO.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String idNo = declarationForm.getIdNo();
                contentStream.showText(idNo);
                contentStream.endText();
            }
            else if ("3".equals(declarationForm.getIdType()) || ("4".equals(declarationForm.getIdType()) && declarationForm.getExpiryDate() != null && declarationForm.getExpiryDate().after(oneYearLater))) {
                // 個人(國民身分證、居留證效期一年以上)
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("V");
                contentStream.endText();

                // 身分證號或統一證號(ID No.)
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ID_NO.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ID_NO.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String idNo = declarationForm.getIdNo();
                contentStream.showText(idNo);
                contentStream.endText();
                if (declarationForm.getBirthDay() != null) {
                    // 出生日期(年)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_BIRTH_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_BIRTH_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String birthDayYear = getYearFormat.format(declarationForm.getBirthDay());
                    contentStream.showText(birthDayYear);
                    contentStream.endText();

                    // 出生日期(月)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_BIRTH_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_BIRTH_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String birthDayMonth = getMonthFormat.format(declarationForm.getBirthDay());
                    contentStream.showText(birthDayMonth);
                    contentStream.endText();

                    // 出生日期(日)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_BIRTH_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_BIRTH_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String birthDayDate = getDayFormat.format(declarationForm.getBirthDay());
                    contentStream.showText(birthDayDate);
                    contentStream.endText();
                }

                // 居留證發給日期不為空
                if (declarationForm.getIssuanceDate() != null) {
                    // 居留證發給日期(年)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ISSUANCE_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ISSUANCE_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String issuanceYear = getYearFormat.format(declarationForm.getIssuanceDate());
                    contentStream.showText(issuanceYear);
                    contentStream.endText();

                    // 居留證發給日期(月)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ISSUANCE_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ISSUANCE_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String issuanceMonth = getMonthFormat.format(declarationForm.getIssuanceDate());
                    contentStream.showText(issuanceMonth);
                    contentStream.endText();

                    // 居留證發給日期(日)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ISSUANCE_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_ISSUANCE_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String issuanceDate = getDayFormat.format(declarationForm.getIssuanceDate());
                    contentStream.showText(issuanceDate);
                    contentStream.endText();
                }

                // 居留證到期日期不為空
                if (declarationForm.getExpiryDate() != null) {
                    // 居留證到期日期(年)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_EXPIRY_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_EXPIRY_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String expiryYear = getYearFormat.format(declarationForm.getExpiryDate());
                    contentStream.showText(expiryYear);
                    contentStream.endText();

                    // 居留證到期日期(月)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_EXPIRY_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_EXPIRY_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String expiryMonth = getMonthFormat.format(declarationForm.getExpiryDate());
                    contentStream.showText(expiryMonth);
                    contentStream.endText();

                    // 居留證到期日期(日)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_EXPIRY_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_3_EXPIRY_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String expiryDate = getDayFormat.format(declarationForm.getExpiryDate());
                    contentStream.showText(expiryDate);
                    contentStream.endText();
                }
            }
            else if ("4".equals(declarationForm.getIdType())) {
                // 個人(國民身分證、居留證效期一年以上)
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("V");
                contentStream.endText();

                // 國別
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_NATIONALITY.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_NATIONALITY.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String nation = declarationForm.getNation();
                contentStream.showText(nation);
                contentStream.endText();

                // 證號或護照編號
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_PASSPORT.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_PASSPORT.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String idNo = declarationForm.getIdNo();
                contentStream.showText(idNo);
                contentStream.endText();

                // 出生日期(年)
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_BIRTH_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_BIRTH_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String birthDayYear = getYearFormat.format(declarationForm.getBirthDay());
                contentStream.showText(birthDayYear);
                contentStream.endText();

                // 出生日期(月)
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_BIRTH_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_BIRTH_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String birthDayMonth = getMonthFormat.format(declarationForm.getBirthDay());
                contentStream.showText(birthDayMonth);
                contentStream.endText();

                // 出生日期(日)
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_BIRTH_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_BIRTH_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String birthDayDate = getDayFormat.format(declarationForm.getBirthDay());
                contentStream.showText(birthDayDate);
                contentStream.endText();

                // 居留證發給日期不為空
                if (declarationForm.getIssuanceDate() != null) {
                    // 居留證發給日期(年)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_ISSUANCE_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_ISSUANCE_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String issuanceYear = getYearFormat.format(declarationForm.getIssuanceDate());
                    contentStream.showText(issuanceYear);
                    contentStream.endText();

                    // 居留證發給日期(月)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_ISSUANCE_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_ISSUANCE_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String issuanceMonth = getMonthFormat.format(declarationForm.getIssuanceDate());
                    contentStream.showText(issuanceMonth);
                    contentStream.endText();

                    // 居留證發給日期(日)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_ISSUANCE_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_ISSUANCE_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String issuanceDate = getDayFormat.format(declarationForm.getIssuanceDate());
                    contentStream.showText(issuanceDate);
                    contentStream.endText();
                }

                // 居留證到期日期不為空
                if (declarationForm.getExpiryDate() != null) {
                    // 居留證到期日期(年)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_EXPIRY_YEAR.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_EXPIRY_YEAR.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String expiryYear = getYearFormat.format(declarationForm.getExpiryDate());
                    contentStream.showText(expiryYear);
                    contentStream.endText();

                    // 居留證到期日期(月)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_EXPIRY_MONTH.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_EXPIRY_MONTH.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String expiryMonth = getMonthFormat.format(declarationForm.getExpiryDate());
                    contentStream.showText(expiryMonth);
                    contentStream.endText();

                    // 居留證到期日期(日)
                    x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_EXPIRY_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
                    y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_ID_TYPE_4_EXPIRY_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
                    contentStream.beginText();
                    contentStream.newLineAtOffset(x, y);
                    String expiryDate = getDayFormat.format(declarationForm.getExpiryDate());
                    contentStream.showText(expiryDate);
                    contentStream.endText();
                }
            }

            // 7. 外匯支出或交易性質
            // 其他匯出款項(應具體詳填性質)
            String remittance = StringUtils.EMPTY;
            // 已是否查扣來判斷勾選項目，需查扣則勾選 『外匯收入或交易性質-其他匯入款項(勾選)』，否則勾選『外匯收入或交易性質-出口貨品收入或對非居住民提供服務收入(勾選)』
            Integer remitType = declarationForm.isPermit() ? REMIT_TYPE_3 : REMIT_TYPE_1_AND_2;
            if (declarationForm.getRemittance() != null && declarationForm.getRemittance().indexOf("--") > 0) {
                // REMITTANCE 外匯收入或交易性質[說明--大類--小類--外匯性質別]
                // 改為不顯示大類，顯示如下 『小類-中文說明』
                String[] remittanceArr = declarationForm.getRemittance().split("--");
                if (remittanceArr.length >= 3) {
                    // 小類
                    if (!"NA".equals(remittanceArr[2])) {
                        remittance += (remittanceArr[2] + "-");
                    }
                    // 中文說明
                    remittance += remittanceArr[0];
                }
            }
            else {
                remittance = declarationForm.getRemittance();
            }

            if (remitType.equals(REMIT_TYPE_1_AND_2)) {
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_1.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_1.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("V");
                contentStream.endText();
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_1_CONTENT.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_1_CONTENT.name()).getY(declarationForm.getSettlement()); // y 座標
            }
            else {
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_2.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_2.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("V");
                contentStream.endText();
                x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_2_CONTENT.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_RECEIPTS_OF_TRANSACTIONS_2_CONTENT.name()).getY(declarationForm.getSettlement()); // y 座標
            }
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(remittance);
            contentStream.endText();

            // 將字串轉換為數字，然後格式化
            String formattedNumber = IBUtils.formatMoneyStr(declarationForm.getAmount(), 2);
            String formattedDate = DateUtils.getCEDateStr(declarationForm.getTxDate());
            // 遇 0000000 則放空值
            String caseNo = "0000000".equals(declarationForm.getCaseNo()) ? "" : declarationForm.getCaseNo();

            // 是否已查扣才顯示以下資訊
            // 原本判斷isPermit欄位來決定是否顯示查扣項目，後來改成判斷查扣狀態PermitStatus==1才顯示
            if ("1".equals(declarationForm.getPermitStatus())) {

                // 右方註釋＠匯入/匯出
                x = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_REMITTANCE.name()).getX(declarationForm.getSettlement()); // x坐标
                y = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_REMITTANCE.name()).getY(declarationForm.getSettlement()); // y坐标
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String remittanceToward = "S".equalsIgnoreCase(declarationForm.getSettlement()) ? "OUTWARD" : "INWARD";
                contentStream.showText("@" + remittanceToward);
                contentStream.endText();

                // 右方註釋＠身份別 統一編號
                x = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_IDTYPE.name()).getX(declarationForm.getSettlement()); // x坐标
                y = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_IDTYPE.name()).getY(declarationForm.getSettlement()); // y坐标
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                if (declarationForm.getPermitIdno() != null) {
                    contentStream.showText("@" + declarationForm.getqId() + " " + declarationForm.getPermitIdno());
                }
                else {
                    contentStream.showText("@" + declarationForm.getqId() + " " + declarationForm.getIdNo());
                }
                contentStream.endText();

                // 右方註釋＠幣別 本次查扣金額
                x = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_AMOUNT_WITHHELD.name()).getX(declarationForm.getSettlement()); // x坐标
                y = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_AMOUNT_WITHHELD.name()).getY(declarationForm.getSettlement()); // y坐标
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                if (declarationForm.getPermitAmt() != null) {
                    // 如有傳入等值美金則顯示等值美金，此處無條件捨去小數位
                    String formattedOriAmt = IBUtils.formatMoneyStr(declarationForm.getPermitAmt(), 0);
                    contentStream.showText("@USD " + formattedOriAmt);
                }
                else {
                    contentStream.showText("@" + declarationForm.getCurrency() + " " + formattedNumber);
                }
                contentStream.endText();

                // 右方註釋＠查扣結果
                x = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_WITHHELD_RESULT.name()).getX(declarationForm.getSettlement()); // x坐标
                y = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_WITHHELD_RESULT.name()).getY(declarationForm.getSettlement()); // y坐标
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                if (!"1".equals(declarationForm.getPermitStatus())) {
                    contentStream.showText("@RESULT:REJECT");
                }
                else {
                    contentStream.showText("@RESULT:PERMIT");
                }
                contentStream.endText();

                // 右方註釋＠送件編號 字軌 日期
                x = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_SUBMIT_NO.name()).getX(declarationForm.getSettlement()); // x坐标
                y = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_SUBMIT_NO.name()).getY(declarationForm.getSettlement()); // y坐标
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                String exchangeHead = declarationForm.getExchangeNo() != null && declarationForm.getExchangeNo().length() >= 4 ? declarationForm.getExchangeNo().substring(0, 4) : "";
                contentStream.showText("@" + caseNo + " " + exchangeHead + " " + formattedDate);
                contentStream.endText();

                // 右方註釋＠結匯匯款分類 細分類 原匯款分類
                x = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_CATEGORY.name()).getX(declarationForm.getSettlement()); // x坐标
                y = Enum.valueOf(enumType, DeclarationFormEnum.ANNOTATION_CATEGORY.name()).getY(declarationForm.getSettlement()); // y坐标
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText("@" + declarationForm.getCategory() + " " + declarationForm.getSubdivision() + " " + declarationForm.getClassO());
                contentStream.endText();
            }

            // 8. 匯款金額 幣別 + 金額
            x = Enum.valueOf(enumType, DeclarationFormEnum.AMOUNT_REMITTED.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.AMOUNT_REMITTED.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(declarationForm.getCurrency() + " " + formattedNumber);
            contentStream.endText();

            // 9. 受款地區國別
            x = Enum.valueOf(enumType, DeclarationFormEnum.REMITTED_FROM_COUNTRY.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.REMITTED_FROM_COUNTRY.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(declarationForm.getCountries());
            contentStream.endText();

            // 10. 地址
            x = Enum.valueOf(enumType, DeclarationFormEnum.ADDRESS.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.ADDRESS.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(declarationForm.getAddress());
            contentStream.endText();

            // 11. 電話
            x = Enum.valueOf(enumType, DeclarationFormEnum.TELEPHONE.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.TELEPHONE.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(declarationForm.getTel());
            contentStream.endText();

            // 12. 送件編號
            // 顯示條件，查扣狀態為1時才顯示送件編號
            if ("1".equals(declarationForm.getPermitStatus())) {
                x = Enum.valueOf(enumType, DeclarationFormEnum.CASE_NO.name()).getX(declarationForm.getSettlement()); // x 座標
                y = Enum.valueOf(enumType, DeclarationFormEnum.CASE_NO.name()).getY(declarationForm.getSettlement()); // y 座標
                contentStream.beginText();
                contentStream.newLineAtOffset(x, y);
                contentStream.showText(caseNo);
                contentStream.endText();
            }

            // 13. 外匯水單編號 字軌為前四碼
            x = Enum.valueOf(enumType, DeclarationFormEnum.EXCHANGE_NO.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.EXCHANGE_NO.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(declarationForm.getExchangeNo());
            contentStream.endText();

            // 14. 申報義務人(電子簽章)
            x = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_SIGNATURE.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.DECLARANT_SIGNATURE.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(name + "(電子簽章)");
            contentStream.endText();

            // 15. 銀行業或外匯證券商負責輔導申報義務人員簽章
            x = Enum.valueOf(enumType, DeclarationFormEnum.BANKING_SIGNATURE.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.BANKING_SIGNATURE.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(declarationForm.getBankingSignature());
            contentStream.endText();

            // 16. 網路申報英文
            x = Enum.valueOf(enumType, DeclarationFormEnum.BANKING_SIGNATURE.name()).getX(declarationForm.getSettlement()) - 18; // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.BANKING_SIGNATURE.name()).getY(declarationForm.getSettlement()) - 12; // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.setFont(font, 8);
            contentStream.showText("Internet Declaration");
            contentStream.endText();

            // 17. 銀行業或外匯證券商簽章及日期
            x = Enum.valueOf(enumType, DeclarationFormEnum.BANKING_DSF_SEAL_AND_DATE.name()).getX(declarationForm.getSettlement()); // x 座標
            y = Enum.valueOf(enumType, DeclarationFormEnum.BANKING_DSF_SEAL_AND_DATE.name()).getY(declarationForm.getSettlement()); // y 座標
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            String userLocale = IBUtils.getExecContext().get(MDCKey.locale.name());
            if (StringUtils.isBlank(userLocale)) {
                userLocale = Locale.TAIWAN.toString();
            }
            String formattedTxnDate = DateUtils.formatDateToChinese(declarationForm.getTxDate(), userLocale);
            contentStream.setFont(font, 10);
            contentStream.showText("台北富邦銀行 " + formattedTxnDate);
            contentStream.endText();

            contentStream.close();

            return document;
        }
        catch (Exception ex) {
            logger.error("找不到檔案 pdf/DeclarationForm...pdf 或 /font/kai.ttf", ex);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E> & IDeclarationFormEnum> Class<E> getDeclarationFormEnum(String docVer, DeclarationPageEnum page) {
        String enumTypeName = "com.tfb.aibank.chl.common.pdf.declarationform.model.DeclarationFormEnum_" + docVer + "_" + page;
        try {
            Class<?> enumClass = Class.forName(ValidateParamUtils.validParam(enumTypeName));
            if (IDeclarationFormEnum.class.isAssignableFrom(enumClass) && Enum.class.isAssignableFrom(enumClass)) {
                return (Class<E>) enumClass;
            }
            else {
                throw new IllegalArgumentException(enumTypeName + " does not implement IDeclarationFormEnum interface or is not an Enum.");
            }
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found: " + enumTypeName, e);
        }
    }

    public static byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        // 將 InputStream 讀取到 buffer 中，然後寫入 ByteArrayOutputStream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        // 將 ByteArrayOutputStream 轉換為 byte[]
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 是否字集資源
     * 
     * @param declarationForm
     * @param font
     * @return
     */
    public static boolean hasGlyph(DeclarationForm declarationForm, PDType0Font font) {
        String declarationFormContent = JsonUtils.getJson(declarationForm);

        try {
            font.encode(declarationFormContent);
        }
        catch (Exception e) {
            // java.lang.IllegalArgumentException: No glyph for U+83D3 (菓)... 問題
            // 必須用Exception 包不可用 IOException
            logger.warn("缺少字形 ", e);
            return false;
        }

        return true;
    }

}
