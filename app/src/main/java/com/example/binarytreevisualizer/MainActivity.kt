package com.example.binarytreevisualizer

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyCustomView(this))
    }
}
class MyCustomView(context: Context) : View(context){
    override fun onDraw(canvas: android.graphics.Canvas?) {
        super.onDraw(canvas)
        val textpaint = Paint()
        val nodepaint = Paint()
        var i : Int = 1
        var k : Int = 1
        var l : Int = 1
        var z : Int = 0
        val xPos : Float = width.toFloat()
        val yPos : Float = 100f
        val rad : Float = 50f

        fun isPowerOfTwo (i : Int) : Boolean{
            return (i and (i - 1)) == 0;
        }
        fun paintNode(testArray: Array<Int>) {
            nodepaint.isAntiAlias = true
            nodepaint.color = Color.BLACK
            nodepaint.style = Paint.Style.FILL_AND_STROKE
            textpaint.isAntiAlias = true
            textpaint.color = Color.WHITE
            textpaint.textSize = 30f
            val prevList : MutableList<Float> = arrayListOf()
            while (i < testArray.size) {
                if (isPowerOfTwo(i)) {
                    k++
                    l = 1
                } else { l += 2 }
                var curX = xPos*l/2f.pow(k-1)
                var curY = yPos*k
                prevList.add(curX)
                canvas?.drawCircle(curX, curY, rad, nodepaint)
                canvas?.drawText("${testArray[i]}", curX, curY, textpaint)
                if (i > 1) {
                    canvas?.drawLine(prevList[z-1], yPos*(k-1), curX, curY, nodepaint)
                }
                if (i%2 == 1) { z++ }
                i++
            }
        }
        val testArray = arrayOf(0,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0)
        paintNode(testArray)
    }
}