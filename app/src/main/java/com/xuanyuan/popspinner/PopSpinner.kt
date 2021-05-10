package com.xuanyuan.popspinner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


/**
 * @author 罗发新
 * 创建时间：2021/4/29  13:38
 * 邮件：424533553@qq.com
 * CSDN：https://blog.csdn.net/luo_boke
 *
 * 更新时间：2021/4/29  13:38
 * 文件说明：因为Android系统自带的Spinner不能满足使用，特意封装一个自定义的下拉列表，可大量自定扩展
 */
class PopSpinner<T> : LinearLayout {
    /**
     * 下拉监听接口
     */
    var listener: PopSpinnerItemClickListener<T>? = null

    /**
     * 下拉View是否已展开
     */
    var isShowing = false

    @JvmName("setListener1")
    fun setListener(listener: PopSpinnerItemClickListener<T>) {
        this.listener = listener
    }

    lateinit var tvValue: TextView
    private lateinit var btDropdown: ImageView

    /**
     * 常规颜色
     */
    private val mNormalColor = 0

    /**
     * 选中颜色
     */
    private val mSelectedColor = 0
    private val mcontext: Context

    /**
     * 数据集合
     */
    private var mItems: List<T>? = null

    /**
     * spinner弹出框
     */
    private lateinit var mSpinerPopWindow: PopWindow<T>
    lateinit var mAdapter: PopListAdapter<T>

    /**
     * 弹出view
     */
    private lateinit var view: View

    constructor(context: Context) : super(context) {
        mcontext = context
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mcontext = context
        init()
    }

    private fun init() {
        val mInflater = LayoutInflater.from(mcontext)
        view = mInflater.inflate(R.layout.layout_pop_spinner, null)
        addView(view)
        tvValue = view.findViewById<View>(R.id.tv_value) as TextView
        btDropdown = view.findViewById<View>(R.id.btnDropdown) as ImageView
        tvValue.setOnClickListener(spinnerOnClickListener)
        btDropdown.setOnClickListener(spinnerOnClickListener)

        mSpinerPopWindow = PopWindow(mcontext)
        mAdapter = PopListAdapter<T>()
        mAdapter.setListener(object : PopSpinnerItemClickListener<T> {
            override fun onItemSelected(position: Int, t: T, value: String) {
                tvValue.text = value
                listener?.onItemSelected(position, t, value)
            }
            override fun setContent(t: T): String {
                val value = listener?.setContent(t)
                return value ?: ""
            }
        })
    }

    /**
     * 设置控件是否可用
     * @param enable true:可用
     */
    fun setEnable(enable: Boolean) {
        tvValue.isEnabled = enable
        btDropdown.isEnabled = enable
    }

    /**
     * 设置text
     * @param text 内容行需要设置的内容
     */
    fun setText(text: String) {
        tvValue.text = text
    }

    /**
     * @param text 获得文本信息
     * @return 返回文本行中的内容
     */
    fun getText(text: String): String {
        return tvValue.text.toString()
    }

    /**
     * 自定义的 MySpinner 被点击
     */
    private val spinnerOnClickListener = OnClickListener {
        isShowing = if (isShowing) {
            dismiss()
            false
        } else {
            showSpinWindow()
            true
        }
    }

    fun setData(datas: List<T>) {
        mItems = datas
        mAdapter.notifyDataSetChanged()
    }

    private fun showSpinWindow() {
        mAdapter.setDatas(mItems)
        mSpinerPopWindow.setAdatper(mAdapter)

        this.mSpinerPopWindow.width = view.width
        if (mItems == null || mItems?.size == 0) {
            this.mSpinerPopWindow.height = 0
        }
        this.mSpinerPopWindow.showAsDropDown(view)
    }

    fun dismiss() {
        this.mSpinerPopWindow.dismiss()
    }

}