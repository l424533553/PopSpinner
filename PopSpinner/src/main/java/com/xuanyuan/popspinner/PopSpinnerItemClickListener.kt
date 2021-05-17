package com.xuanyuan.popspinner

/**
 * @author 罗发新
 * 创建时间：2021/4/29  13:38
 * 邮件：424533553@qq.com
 * CSDN：https://blog.csdn.net/luo_boke
 *
 * 更新时间：2021/4/29  13:38
 * 文件说明：因为Android系统自带的Spinner不能满足使用，特意封装一个自定义的下拉列表，可大量自定扩展
 */
interface PopSpinnerItemClickListener<T> {
    /**
     * popList 点击监听
     *
     * @param position 列表位置
     * @param t        实体类
     * @param value    值
     */
    fun onItemSelected(position: Int, t: T, value: String)

    /**
     * 设置 内容
     *
     * @param t 内容包
     * @return 返回下拉列表要显示的内容
     */
    fun setContent(t: T): String
}