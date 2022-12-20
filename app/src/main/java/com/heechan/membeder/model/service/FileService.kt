package com.heechan.membeder.model.service

import com.heechan.membeder.model.data.file.FileUploadRes
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileService {
    @Multipart
    @POST("/file/upload")
    suspend fun fileUpload(
        @Part file : MultipartBody.Part,
    ) : Response<FileUploadRes>
}