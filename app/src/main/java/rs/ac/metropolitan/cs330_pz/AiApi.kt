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