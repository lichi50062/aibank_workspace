# Deployment Shell Scripts

本目錄包含一系列用於 Kubernetes 環境維運的 shell 腳本。

**注意:** 大部分的腳本預設的 `NAMESPACE` 為 `tpebnknanot`，請根據您的目標環境進行修改。

---

## 使用說明

### `clean-sit2-cache.sh`

清除 SIT2 環境的相關快取。

**使用方法:**
```bash
./clean-sit2-cache.sh
```

---

### `killTerminatingpod.sh`

強制刪除在 `tpebnknanot` 命名空間中處於 "Terminating" 狀態的 Pod。

**使用方法:**
```bash
./killTerminatingpod.sh
```

---

### `querybatch.sh`

查詢正在運行的批次作業。執行時會提示輸入目標版本。

**使用方法:**
```bash
./querybatch.sh
```

---

### `resetNodeNotIn-new-v2.sh`

更新 `biz`, `chl`, `oauth`, `gateway`, `batch`, `admin` 的 Deployment，使其 Pod 避開在指定的 Node 上調度。

**使用方法:**
```bash
./resetNodeNotIn-new-v2.sh <NODE_NAME>
```
- `<NODE_NAME>`: 要避開的節點名稱。

---

### `resetNodeNotIn-new.sh`

更新 `biz` 和 `chl` 的 Deployment，使其 Pod 避開在指定的 Node 上調度。

**使用方法:**
```bash
./resetNodeNotIn-new.sh <NODE_NAME>
```
- `<NODE_NAME>`: 要避開的節點名稱。

---

### `resetNodeNotIn.sh`

與 `resetNodeNotIn-new.sh` 類似，但只會更新狀態不是 "Running" 的 Pod 所屬的 Deployment。

**使用方法:**
```bash
./resetNodeNotIn.sh <NODE_NAME>
```
- `<NODE_NAME>`: 要避開的節點名稱。

---

### `resetNodeNotInMul.sh`

`resetNodeNotIn.sh` 的變形，硬性編碼避開 `tpebnkcldatu1w01t` 和 `tpebnkcldatu1w05t` 這兩個節點。

**使用方法:**
```bash
./resetNodeNotInMul.sh '"tpebnkcldatu1w01t","tpebnkcldatu1w05t"'
```

---

### `restart_biz.sh`

縮放名稱包含 `biz` 和 `v1` 的 Deployment 的 Pod 數量。

**使用方法:**
```bash
./restart_biz.sh <REPLICA_COUNT>
```
- `<REPLICA_COUNT>`: 目標 Pod 數量 (例如: 0 或 1)。

---

### `restart_biz_sit2.sh`

縮放名稱包含 `biz` 和 `sit2` 的 Deployment 的 Pod 數量。

**使用方法:**
```bash
./restart_biz_sit2.sh <REPLICA_COUNT>
```
- `<REPLICA_COUNT>`: 目標 Pod 數量 (例如: 0 或 1)。

---

### `restart_chl.sh`

縮放名稱包含 `chl` 和 `v1` 的 Deployment 的 Pod 數量。

**使用方法:**
```bash
./restart_chl.sh <REPLICA_COUNT>
```
- `<REPLICA_COUNT>`: 目標 Pod 數量 (例如: 0 或 1)。

---

### `restart_chl_sit2.sh`

縮放名稱包含 `chl` 和 `sit2` 的 Deployment 的 Pod 數量。

**使用方法:**
```bash
./restart_chl_sit2.sh <REPLICA_COUNT>
```
- `<REPLICA_COUNT>`: 目標 Pod 數量 (例如: 0 或 1)。

---

### `restartfornocpu.sh`

此腳本用於重啟 `biz` 和 `chl` 的 Pod。它會先將 Pod 數量縮減到 0，等待一段時間後，再擴展回 1。主要用於解決 Pod CPU 異常問題。

**使用方法:**
```bash
./restartfornocpu.sh
```
