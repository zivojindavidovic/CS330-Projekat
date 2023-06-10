package rs.ac.metropolitan.cs330_pz.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rs.ac.metropolitan.cs330_pz.model.AiRequest

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