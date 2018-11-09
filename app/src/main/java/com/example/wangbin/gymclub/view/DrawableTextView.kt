package com.example.wangbin.gymclub.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import com.example.wangbin.gymclub.R

class DrawableTextView :TextView{
    private var mDrawableLeft:Int? = null
    private var mDrawableRight:Int? = null
    private var mDrawableTop:Int? = null
    private var mDrawableBottom:Int? = null
    private var mContext:Context? = null
    private var mDrawableWidth:Int? = null
    private var mDrawableHeight:Int? = null

    constructor(context:Context?):this(context,null)

    constructor(context: Context?,attrs: AttributeSet?):this(context,attrs,0)

    constructor(context: Context?,attrs: AttributeSet?,styledef:Int):super(context,attrs,styledef){
        this.mContext = context
        if(attrs != null){
            val array:TypedArray? = context?.obtainStyledAttributes(attrs, R.styleable.DrawableTextView)
            mDrawableLeft = array?.getResourceId(R.styleable.DrawableTextView_leftDrawable,0)
            mDrawableRight = array?.getResourceId(R.styleable.DrawableTextView_rightDrawable,0)
            mDrawableBottom = array?.getResourceId(R.styleable.DrawableTextView_bottomDrawable,0)
            mDrawableTop = array?.getResourceId(R.styleable.DrawableTextView_topDrawable,0)
            mDrawableWidth = array?.getDimensionPixelSize(R.styleable.DrawableTextView_width,30)
            mDrawableHeight = array?.getDimensionPixelSize(R.styleable.DrawableTextView_height,30)
            array?.recycle()
            init()
        }
    }

    fun init():Unit{
        var drawableLeft: Drawable? = null
        if(mDrawableLeft!=0){
            drawableLeft = ContextCompat.getDrawable(context, mDrawableLeft!!)
            drawableLeft?.bounds = Rect(0,0,mDrawableWidth!!,mDrawableHeight!!)
        }
        var drawableRight: Drawable? = null
        if(mDrawableRight!=0){
            drawableRight = ContextCompat.getDrawable(context, mDrawableRight!!)
            drawableRight?.bounds = Rect(0,0,mDrawableWidth!!,mDrawableHeight!!)
        }
        var drawableTop: Drawable? = null
        if(mDrawableTop!=0){
            drawableTop = ContextCompat.getDrawable(context, mDrawableTop!!)
            drawableTop?.bounds = Rect(0,0,mDrawableWidth!!,mDrawableHeight!!)
        }
        var drawableBottom: Drawable? = null
        if(mDrawableBottom!=0){
            drawableBottom = ContextCompat.getDrawable(context, mDrawableBottom!!)
            drawableBottom?.bounds = Rect(0,0,mDrawableWidth!!,mDrawableHeight!!)
        }
        setCompoundDrawables(drawableLeft,drawableTop,drawableRight,drawableBottom)

    }

}
