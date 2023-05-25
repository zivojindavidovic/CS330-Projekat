package rs.ac.metropolitan.cs330_pz

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AiApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer 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"
    )
    @POST("/v1/completions")
    fun getCompletion(
        @Body requestBody: AiRequest
    ): Call<AiResponse>
}