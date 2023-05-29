package rs.ac.metropolitan.cs330_pz.api

data class AiResponse(
    val choices: List<Choice>
)
data class Choice(
    val text: String
)
