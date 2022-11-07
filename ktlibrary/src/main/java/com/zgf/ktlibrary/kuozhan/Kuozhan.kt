package com.zgf.ktlibrary.kuozhan

import android.util.Log

/**
 * Kotlin的扩展函数
 * run/with/also/apply/let/takeIf/takeUnless/repeat
 */
class Kuozhan {

    private val list: ArrayList<Int>? = null
    private var string: String = "hello world!"

    // run 内可以直接使用该对象的属性和方法
    // let 和 with 的结合体，返回值为最后一行
    fun run() {
        list?.run {
            Log.e("zgf", "===========$size")
        }
        var data = string.run {
            toCharArray()
            length
            startsWith("h")
            endsWith("!")
        }
    }

    // apply 在内可以直接使用该对象的属性和方法
    // 返回值为自己
    fun apply() {
        var data = string.apply {
            toCharArray()
            length
            startsWith("h")
            endsWith("!")
        }
    }

    // with 将对象传入，在内可以直接使用该对象的属性和方法
    // 返回值为最后一行
    fun with() {
        val data = with(string) {
            toCharArray()
            length
            startsWith("h")
            endsWith("!")
        }
    }

    // let 内用 it 来访问该对象的属性和方法
    // ?. 判空
    // 返回值为最后一行
    fun let() {
        var data = string.let {
            it.toCharArray()
            it.length
            it.startsWith("h")
            it.endsWith("!")
        }

        list?.let {
            it.size
            it.add(1)
            it[0]
        }
    }

    // also 内用 it 表示，可以使用 it 访问该对象的属性和方法
    // 返回值为自己
    fun also() {
        var data = string.also {
            it.toCharArray()
            it.length
            it.startsWith("h")
            it.endsWith("!")
        }
    }

    // 指定内部局部变量 it 为 str
    fun also1() {
        string.also { str: String ->
            str.length
            str.startsWith("h")
            str.endsWith("!")
        }
    }

}