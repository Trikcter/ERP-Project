package ru.samgtu.erp.utils

class StringUtils {
    companion object {
        private fun getNameArray(fio: String): List<String> {
            return fio.split(" ")
        }

        fun getShortFio(name: String, surname: String, secondName: String?): String {
            return if (secondName == "") {
                surname + " " + name[0] + "."
            } else {
                surname + " " + name[0] + ". " + (secondName?.get(0) ?: "") + "."
            }
        }

        fun getNameFromFio(fio: String): String {
            return this.getNameArray(fio)[1]
        }

        fun getSurnameFromFio(fio: String): String {
            return this.getNameArray(fio)[0]
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