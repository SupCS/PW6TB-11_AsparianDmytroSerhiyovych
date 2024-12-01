package ua.asparian.practice6

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import androidx.compose.ui.Modifier
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.ceil

// Дані для кожного електроприводу (ЕП)
@Stable
data class EquipmentData(
    var equipmentName: String = "",
    var efficiencyRating: String = "",
    var loadPowerFactor: String = "",
    var loadVoltage: String = "",
    var quantity: String = "",
    var nominalPower: String = "",
    var usageFactor: String = "",
    var reactivePowerCoefficient: String = "",

    var multipliedPower: String = "",
    var current: String = "",
)

// Форма для введення даних про один ЕП
@Composable
fun EquipmentForm(equipmentData: EquipmentData) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = equipmentData.equipmentName,
        onValueChange = { equipmentData.equipmentName = it },
        label = { Text("Назва обладнання") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.efficiencyRating,
        onValueChange = { equipmentData.efficiencyRating = it },
        label = { Text("ККД обладнання (ηн)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.loadPowerFactor,
        onValueChange = { equipmentData.loadPowerFactor = it },
        label = { Text("Коефіцієнт потужності (cos φ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.loadVoltage,
        onValueChange = { equipmentData.loadVoltage = it },
        label = { Text("Напруга (Uн, кВ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.quantity,
        onValueChange = { equipmentData.quantity = it },
        label = { Text("Кількість (шт)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.nominalPower,
        onValueChange = { equipmentData.nominalPower = it },
        label = { Text("Номінальна потужність (Рн, кВт)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.usageFactor,
        onValueChange = { equipmentData.usageFactor = it },
        label = { Text("Коефіцієнт використання") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = equipmentData.reactivePowerCoefficient,
        onValueChange = { equipmentData.reactivePowerCoefficient = it },
        label = { Text("tgφ (коефіцієнт реактивної потужності)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
}

// Основний калькулятор для обчислення характеристик ЕП
@Preview
@Composable
fun EquipmentCalculator() {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    // Жорстко заданий список ЕП
    val equipmentList = listOf(
        EquipmentData(
            equipmentName = "Шліфувальний верстат",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "4",
            nominalPower = "20",
            usageFactor = "0.15",
            reactivePowerCoefficient = "1.33"
        ),
        EquipmentData(
            equipmentName = "Свердлильний верстат",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "2",
            nominalPower = "14",
            usageFactor = "0.12",
            reactivePowerCoefficient = "1"
        ),
        EquipmentData(
            equipmentName = "Фугувальний верстат",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "4",
            nominalPower = "42",
            usageFactor = "0.15",
            reactivePowerCoefficient = "1.33"
        ),
        EquipmentData(
            equipmentName = "Циркулярна пила",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "1",
            nominalPower = "36",
            usageFactor = "0.3",
            reactivePowerCoefficient = "1.52"
        ),
        EquipmentData(
            equipmentName = "Прес",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "1",
            nominalPower = "20",
            usageFactor = "0.5",
            reactivePowerCoefficient = "0.75"
        ),
        EquipmentData(
            equipmentName = "Полірувальний верстат",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "1",
            nominalPower = "40",
            usageFactor = "0.2",
            reactivePowerCoefficient = "1"
        ),
        EquipmentData(
            equipmentName = "Фрезерний верстат",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "2",
            nominalPower = "32",
            usageFactor = "0.2",
            reactivePowerCoefficient = "1"
        ),
        EquipmentData(
            equipmentName = "Вентилятор",
            efficiencyRating = "0.92",
            loadPowerFactor = "0.9",
            loadVoltage = "0.38",
            quantity = "1",
            nominalPower = "20",
            usageFactor = "0.65",
            reactivePowerCoefficient = "0.75"
        )
    )
    var Kr by remember { mutableStateOf("1.25") }
    var Kr2 by remember { mutableStateOf("0.7") }

    var sum_of_n_Pn_Kv_product_41 by remember { mutableStateOf(0.0) }
    var kv_group by remember { mutableStateOf("") }
    var eff_ep_amount by remember { mutableStateOf("") }
    var total_department_util_coef by remember { mutableStateOf("") }
    var eff_ep_department_amount by remember { mutableStateOf("") }
    var rozrah_act_nav by remember { mutableStateOf("") }
    var rozrah_react_nav by remember { mutableStateOf("") }
    var full_power by remember { mutableStateOf("") }
    var rozrah_group_strum_shr1 by remember { mutableStateOf("") }
    var rozrah_act_nav_shin by remember { mutableStateOf("") }
    var rozrah_react_nav_shin by remember { mutableStateOf("") }
    var full_power_shin by remember { mutableStateOf("") }
    var rozrah_group_strum_shin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Список ЕП
        equipmentList.forEach { equipment ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                EquipmentForm(equipmentData = equipment)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                // Основні змінні
                var sum_of_n_Pn_Kv_product = 0.0
                var sum_of_n_Pn_product = 0.0
                var sum_of_n_Pn_Pn_product = 0.0
                var group_util_coefficient = 0.0
                var effective_ep_amount = 0.0

                // Розрахунки для кожного ЕП
                equipmentList.forEach { equipment ->
                    // Кількість ЕП
                    val quantity = equipment.quantity.toDouble()
                    // Номінальна потужність
                    val nominalPower = equipment.nominalPower.toDouble()
                    // Розрахунок множення кількості на потужність
                    equipment.multipliedPower = "${quantity * nominalPower}"
                    // Розрахунок струму
                    val current = equipment.multipliedPower.toDouble() /
                            (sqrt(3.0) * equipment.loadVoltage.toDouble() *
                                    equipment.loadPowerFactor.toDouble() *
                                    equipment.efficiencyRating.toDouble())
                    equipment.current = current.toString()

                    // Сума добутків n * Pn * Kv
                    sum_of_n_Pn_Kv_product += equipment.multipliedPower.toDouble() * equipment.usageFactor.toDouble()
                    // Сума добутків n * Pn
                    sum_of_n_Pn_product += equipment.multipliedPower.toDouble()
                    // Сума квадратів Pn
                    sum_of_n_Pn_Pn_product += quantity * nominalPower * nominalPower
                }

                // Групові обчислення
                sum_of_n_Pn_Kv_product_41 = sum_of_n_Pn_Kv_product
                group_util_coefficient = sum_of_n_Pn_Kv_product / sum_of_n_Pn_product
                effective_ep_amount = ceil((sum_of_n_Pn_product * sum_of_n_Pn_product) / sum_of_n_Pn_Pn_product)

                kv_group = group_util_coefficient.toString()
                eff_ep_amount = effective_ep_amount.toString()

                // Додаткові обчислення
                val KrValue = Kr.toDouble()
                val PH = 27.0
                val tan_phi = 1.63
                val Un = 0.28

                val Pp = KrValue * sum_of_n_Pn_Kv_product
                val Qp = group_util_coefficient * PH * tan_phi
                val Sp = sqrt((Pp * Pp) + (Qp * Qp))
                val Ip = Pp / Un

                rozrah_act_nav = Pp.toString()
                rozrah_react_nav = Qp.toString()
                full_power = Sp.toString()
                rozrah_group_strum_shr1 = Ip.toString()

                // Розрахунок для всього цеху
                val KvDepartment = 752.0 / 2330.0
                val n_e = 2330.0 * 2330.0 / 96399.0

                total_department_util_coef = KvDepartment.toString()
                eff_ep_department_amount = n_e.toString()

                // Обчислення для шин
                val KvValue = Kr2.toDouble()
                val PpShin = KvValue * 752.0
                val QpShin = KvValue * 657.0
                val SpShin = sqrt((PpShin * PpShin) + (QpShin * QpShin))
                val IpShin = PpShin / 0.38

                rozrah_act_nav_shin = PpShin.toString()
                rozrah_react_nav_shin = QpShin.toString()
                full_power_shin = SpShin.toString()
                rozrah_group_strum_shin = IpShin.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Груповий коефіцієнт використання: $kv_group", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ефективна кількість ЕП: $eff_ep_amount", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове активне навантаження: $rozrah_act_nav", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове реактивне навантаження: $rozrah_react_nav", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Повна потужність: $full_power", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахунковий груповий струм ШР1: $rozrah_group_strum_shr1", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Коефіцієнт використання цеху в цілому: $total_department_util_coef", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ефективна кількість ЕП цеху в цілому: $eff_ep_department_amount", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове активне навантаження на шинах: $rozrah_act_nav_shin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахункове реактивне навантаження на шинах: $rozrah_react_nav_shin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Повна потужність на шинах: $full_power_shin", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахунковий груповий струм на шинах: $rozrah_group_strum_shin", style = MaterialTheme.typography.bodyLarge)
    }
}