package ru.samgtu.erp.utils

class StringUtils {
    companion object {
        private fun getNameArray(fio: String): List<String> {
            return fio.split(" ")
        }

        fun getNameFromFio(fio: String): String {
            return this.getNameArray(fio)[1]
        }

        fun getSurnameFromFio(fio: String): String {
            return this.getNameArray(fio)[2]
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