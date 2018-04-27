package neon.gabrielcosta.gvcneon.entity.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

class SendMoneyDTO constructor(@SerializedName("ClienteId") val clientId: Long,
    @SerializedName("token") val token: String,
    @SerializedName("valor") val amount: Double)

data class TransferResponseDTO(@SerializedName("Id") val id: Long,
    @SerializedName("ClientId") val clientId: Long,
    @SerializedName("Valor") val amount: Double,
    @SerializedName("Token") val token: String,
    @SerializedName("Data") val date: Date)