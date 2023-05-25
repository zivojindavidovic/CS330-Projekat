package rs.ac.metropolitan.cs330_pz

data class AiResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)
