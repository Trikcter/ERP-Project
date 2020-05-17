package ru.samgtu.erp.utils

class StringUtils {
    companion object {
        private fun getNameArray(fio: String): List<String> {
            return fio.split(" ")
        }

        fun getShortFio(fio: String): String {
            val name = this.getNameFromFio(fio)
            val surname = this.getSurnameFromFio(fio)
            val secondName = this.getSecondNameFromFio(fio)

            return surname + " " + name[1] + ". " + secondName[1] + "."
        }

        fun getNameFromFio(fio: String): String {
            return this.getNameArray(fio)[1]
        }

        fun getSurnameFromFio(fio: String): String {
            return if (this.getNameArray(fio).size == 2) {
                ""
            } else {
                this.getNameArray(fio)[2]
            }
        }

        fun getSecondNameFromFio(fio: String): String {
            return if (this.getNameArray(fio).size == 3) {
                this.getNameArray(fio)[2]
            } else {
                ""
            }
        }
    }
}