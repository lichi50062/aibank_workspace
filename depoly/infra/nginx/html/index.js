	//若以電腦版開啟，則導去APP商店
	var url = window.location.toString();
	
	var pcMacAppStroeLink = "https://apps.apple.com/tw/app/%E5%AF%8C%E9%82%A6%E8%A1%8C%E5%8B%95%E9%8A%80%E8%A1%8C/id964733020";	//mac 系統 app store 網址
	var pcWindowsAppStroeLink = "https://play.google.com/store/apps/details?id=com.fubon.mbank&hl=zh_TW&gl=US";	//windows 系統 app store 網址
	
	
	transferLink(url);
	
	function transferLink(urlStr){
		var u = navigator.userAgent;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android終端或者uc瀏覽器
		var isiOS = !!u.match(/\(i[^;] ;( U;)? CPU. Mac OS X/); //ios終端
		var parameterUrl = ""//url參數
		var menuId = ""; //行銀功能ID
		
		if (window.screen.width<768){
			//小網  		
			if (url.indexOf("?") != -1) {
				parameterUrl = url.split("?")[1]
			}


			var redirected = false;

			window.location.href = "aibank.fubon://?" + parameterUrl; /***開啟app***/
			if(isAndroid){
				window.setTimeout(function(){
					if (!document.webkitHidden && !redirected) {
						redirected = true;						
						window.location.href = "https://play.google.com/store/apps/details?id=com.fubon.mbank&hl=zh_TW"; /***no app，開商店***/
					}
				},10000);
			} else {
				var t = window.setTimeout(function(){
					if (!document.webkitHidden) {
						window.location.href = "https://apps.apple.com/tw/app/%E5%AF%8C%E9%82%A6%E8%A1%8C%E5%8B%95%E9%8A%80%E8%A1%8C/id964733020"; /***no app，開商店***/
					}else {
						window.clearTimeout(t);
					}
				},10000);
			}
					
		} else {

			/** * 是否為mac系统（包含iphone手機） * */ 
			var isMac = function() { 
			return /macintosh|mac os x/i.test(navigator.userAgent); 
			}();


			/** * 是否為windows系统 * */
			var isWindows = function() { 
				return /windows|win32/i.test(navigator.userAgent);
			}(); 
			
			if(isMac){
				//mac
				window.location.href = pcMacAppStroeLink;
			} else if(isWindows){
				//Windows
				window.location.href = pcWindowsAppStroeLink;
			} else {
				alert("unknown");
			}
			
		}
	}

