package com.devnguyen2k1.ai_retouch.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.devnguyen2k1.ai_retouch.data.remote.ApiClient
import com.devnguyen2k1.ai_retouch.domain.entities.RestoredImage
import com.devnguyen2k1.ai_retouch.domain.repository.PhotoRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PhotoRepositoryImpl(
    private val context: Context
) : PhotoRepository {

    override suspend fun restoreImage(imageUri: Uri): RestoredImage? {
        try {
            // Lấy token từ API
            val tokenResponse = ApiClient.tokenService.getToken()
            val token = tokenResponse.data?.emToken

            // Kiểm tra nếu token không hợp lệ hoặc rỗng
            if (token.isNullOrEmpty()) {
                Log.e("API_Error", "Token không hợp lệ hoặc rỗng")
                return null
            }

            Log.d("API_Token", "Token lấy được: $token")

            // Chuyển đổi Uri thành File (giả sử bạn đã thực hiện việc này đúng)
            val file = uriToFile(imageUri)

            // Tạo MultipartBody.Part cho ảnh
            val imagePart = MultipartBody.Part.createFormData(
                "input_image", // Tên của trường trong form data
                file.name, // Tên file từ hệ thống
                file.asRequestBody("image/*".toMediaTypeOrNull()) // Chỉ định MIME type cho ảnh
            )

            // Tạo MultipartBody.Part cho ngôn ngữ
            val langPart = "en".toRequestBody("text/plain".toMediaTypeOrNull())

            // Gọi API để phục hồi ảnh
            val result = ApiClient.restoreService.restoreImage(
                input_image = imagePart,
                lang = langPart,
                token = "Bearer $token",
                userAgent = "SnapEdit/5.2.9 (com.sfun.snapedit; build:626; iOS 17.5.1) Alamofire/5.9.1", // User-Agent
                acceptLanguage = "en-VN;q=1.0, vi-VN;q=0.9, vi-JP;q=0.8, zh-Hans-VN;q=0.7" // Accept-Language
            )


            // Kiểm tra phản hồi từ API
            Log.d("API_Response", "Kết quả trả về từ API: $result")

            // Kiểm tra lỗi từ API nếu ảnh không được trả về
            if (result.outputImage == null) {
                Log.e("API_Response", "API không trả về ảnh khôi phục, kiểm tra lại yêu cầu hoặc API.")
                return null
            }

            // Kiểm tra Base64 của ảnh khôi phục
            val restoredImageBase64 = result.outputImage?.image
            if (restoredImageBase64.isNullOrEmpty()) {
                Log.e("API_Response", "Base64 ảnh khôi phục rỗng hoặc không hợp lệ.")
                return null
            }

            Log.d("API_Response", "Base64 của ảnh đã khôi phục: $restoredImageBase64")

            // Giải mã Base64 và lưu ảnh
            val restoredFile = saveImageFromBase64(restoredImageBase64)

            // Trả về đối tượng RestoredImage với đường dẫn ảnh đã khôi phục
            return RestoredImage(
                originalUri = imageUri,
                restoredImageUrl = restoredFile.absolutePath
            )

        } catch (e: Exception) {
            Log.e("API_Exception", "Lỗi khi gọi restoreImage: ${e.message}", e)
            return null
        }
    }

    // Hàm chuyển Uri thành File
    private fun uriToFile(uri: Uri): File {
        // Kiểm tra quyền đọc bộ nhớ ngoài nếu cần thiết
        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IllegalArgumentException("Không thể đọc được uri: $uri")

        val tempFile = File.createTempFile("image", ".jpg", context.cacheDir)
        try {
            tempFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        } catch (e: Exception) {
            Log.e("File_Error", "Lỗi khi ghi file từ Uri: ${e.message}", e)
            throw e
        }

        tempFile.deleteOnExit()  // Đảm bảo file sẽ bị xóa khi ứng dụng tắt
        return tempFile
    }

    // Hàm lưu ảnh từ Base64
    private fun saveImageFromBase64(base64String: String): File {
        // Giải mã Base64 thành byte array
        val decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT)

        // Tạo file tạm thời trong bộ nhớ cache của ứng dụng
        val outputFile = File(context.cacheDir, "restored_image_${System.currentTimeMillis()}.jpg")
        try {
            java.io.FileOutputStream(outputFile).use { outputStream ->
                outputStream.write(decodedBytes)
            }
            Log.d("File_Saved", "Ảnh đã lưu thành công tại: ${outputFile.absolutePath}")
        } catch (e: Exception) {
            Log.e("File_Error", "Lỗi khi lưu ảnh: ${e.message}", e)
            throw e
        }
        return outputFile
    }
}
