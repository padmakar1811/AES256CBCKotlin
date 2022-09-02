package com.padmakar.aes256cbc

import androidx.annotation.RequiresApi
import android.os.Build
import com.padmakar.aes256cbc.AESUtil
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.lang.RuntimeException
import java.security.Key
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

enum class AESUtil {
    ;

    companion object {
        // private static final String ENCRYPTION_KEY = "9427bfe5b2a07ddfbdc30a254f44fd09bdd216c6ab1b37738c5758e4fa4d114d";
        // private static final String ENCRYPTION_IV = "59ec1871179215f16986aa4e4092cda8";
        /*16 bit Encrption*/
        private const val ENCRYPTION_KEY = "bf3c199c2470cb477d907b1e0917c17b"
        private const val ENCRYPTION_IV = "5183666c72eec9e4"
        @RequiresApi(api = Build.VERSION_CODES.O)
        fun encrypt(src: String): String {
            return try {
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv())
                val encoder = Base64.getEncoder()
                encoder.encodeToString(cipher.doFinal(src.toByteArray()))
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        fun decrypt(src: String?): String {
            var decrypted = ""
            decrypted = try {
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                cipher.init(Cipher.DECRYPT_MODE, makeKey(), makeIv())
                val decoder = Base64.getDecoder()
                String(cipher.doFinal(decoder.decode(src)))
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
            return decrypted
        }

        fun makeIv(): AlgorithmParameterSpec? {
            try {
                // return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
                return IvParameterSpec(ENCRYPTION_IV.substring(0, 16).toByteArray(charset("UTF-8")))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return null
        }

        fun makeKey(): Key? {
            try {
                val key = ENCRYPTION_KEY.substring(0, 32).toByteArray(charset("UTF-8"))
                return SecretKeySpec(key, "AES")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return null
        }
    }
}