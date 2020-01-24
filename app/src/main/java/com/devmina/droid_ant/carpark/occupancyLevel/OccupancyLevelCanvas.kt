package com.devmina.droid_ant.carpark.occupancyLevel


import android.content.Context
import android.graphics.*
import android.graphics.Color.rgb
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.devmina.droid_ant.carpark.R





private const val STROKE_WIDTH = 30f //has to be float

class OccupancyLevelCanvas @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyleAttr:Int=0) : View(context,attrs,defStyleAttr){



    private lateinit var extraCanvas: Canvas


    private lateinit var  srcBitmap : Bitmap

    private lateinit var arc:RectF

    private var total = 0f
    private var amount = 0f
    private var occup = 0f

    private  var color =  ContextCompat.getColor(context, R.color.grayColor)

    var density = context.resources.displayMetrics.density

    init{
        total =  data.totalSpaces
        amount = data.amountSpaces
    }





    private val paint= Paint(Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG or Paint.ANTI_ALIAS_FLAG).apply {
        style =Paint.Style.STROKE //default: FILL
        strokeCap=Paint.Cap.ROUND //default:BUTT
        strokeWidth = STROKE_WIDTH//default Hairline_width(really thin)
    }
    private fun setColor(){

        val percent = Math.abs((amount / total ) * 100)
          occup = Math.abs((percent / 100) * 180)
        color = when {
            //135
            percent <= 75 -> ContextCompat.getColor(context,
                R.color.orangeColor
            )
            //90
            percent <= 50 -> ContextCompat.getColor(context,
                R.color.greenColor
            )
            //180
            else -> ContextCompat.getColor(context, R.color.redColor)
        }
        invalidate()
    }
    //Paint for text values.
    private val mTextPaint  =  Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize =  context.getResources().getDimension(R.dimen.widget_text_value) / density
        textAlign = Paint.Align.CENTER
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if(::srcBitmap.isInitialized) srcBitmap.recycle()
        srcBitmap =Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        extraCanvas = Canvas(srcBitmap)



        arc = RectF(  ( extraCanvas.width  / 2) - 200f  , (extraCanvas.height /2)- 200f   ,  (extraCanvas.width / 2 ) + 200f   ,  (extraCanvas.height / 2) + 200f  )


    }




    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setColor()

        canvas.drawColor(Color.TRANSPARENT)
        //gray arc
        paint.setColor(	rgb(220,220,220))

        canvas.drawArc(arc, 180f, 180f, false, paint)


        paint.setColor(color)

        canvas.drawArc(arc, 180f,occup , false, paint)


        //Draw text value.
        mTextPaint.color = color
      canvas.drawText("$amount", (width / 2f) , ((height - mTextPaint.ascent()) / 2) - 50, mTextPaint)




    }


}