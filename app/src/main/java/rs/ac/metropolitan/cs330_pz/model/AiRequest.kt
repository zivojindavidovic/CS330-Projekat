package rs.ac.metropolitan.cs330_pz.model

data class AiRequest(
    val prompt: String,
    val max_tokens: Int,
    val model: String
)