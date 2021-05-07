package com.xuanyuan.popspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author 罗发新
 * 创建时间：2021/4/29  13:38
 * 邮件：424533553@qq.com
 * CSDN：https://blog.csdn.net/luo_boke
 *
 *
 * 更新时间：2021/4/29  13:38
 *
 *
 * 文件说明：弹出的PopList框
 */
class PopWindow<T>(private val mContext: Context) : PopupWindow(mContext) {
    private lateinit var mListView: RecyclerView
    private var mAdapter: PopListAdapter<T>? = null
    fun setAdatper(adapter: PopListAdapter<T>) {
        mAdapter = adapter
        mListView.adapter = mAdapter
    }

    init {
        init()
    }

    private fun init() {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_spinner_window, null)
        contentView = view
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT


//        setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0x00);
//        setBackgroundDrawable(dw);
        mListView = view.findViewById<View>(R.id.listView) as RecyclerView

        // 初始化 RecyclerView
        val layoutManager = LinearLayoutManager(mContext)
        mListView.layoutManager = layoutManager
    }

    fun refreshData(list: List<T>?, selIndex: Int) {
        if (list != null && selIndex != -1) {
            if (mAdapter != null) {
                //TODO LUOFAXIN
//                mAdapter.refreshData(list, selIndex);
            }
        }
    }


}


