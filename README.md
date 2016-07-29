#自定义view之无所不能的path
最近项目中需要完成以下这个需求

![demo](https://github.com/wzgiceman/PathDemo/blob/master/gif/pp.png)

UI给我了五张图片，我感觉太浪费了，自定义view完全可以做而且适配起来更加的方便
##最终实现效果
* 项目效果

  ![demo](https://github.com/wzgiceman/PathDemo/blob/master/gif/card.gif)

* 扩展

  ![demo](https://github.com/wzgiceman/PathDemo/blob/master/gif/path.gif)

##需要知道技术点
在实现这个过程之前，我们需要了解path的一系列的原理（如果你了解path的用法直接跳过）

###PathMeasure（是一个用来测量Path的类，主要有以下方法)
![demo](https://github.com/wzgiceman/PathDemo/blob/master/gif/path_1.png)

* setPath、 isClosed 和 getLength

这三个方法都如字面意思一样，非常简单，这里就简单是叙述一下，不再过多讲解。
setPath 是 PathMeasure 与 Path 关联的重要方法，效果和 构造函数 中两个参数的作用是一样的。
isClosed 用于判断 Path 是否闭合，但是如果你在关联 Path 的时候设置 forceClosed 为 true 的话，这个方法的返回值则一定为true。
getLength 用于获取 Path 的总长度

* getSegment

```java
//返回值(boolean)    判断截取是否成功	true 表示截取成功，结果存入dst中，false 截取失败，不会改变dst中内容
//startD	开始截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
//stopD	结束截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
//dst	截取的 Path 将会添加到 dst 中	注意: 是添加，而不是替换
//startWithMoveTo	起始点是否使用 moveTo	用于保证截取的 Path 第一个点位置不变
//如果 startD、stopD 的数值不在取值范围 [0, getLength] 内，或者 startD == stopD 则返回值为 false，不会改变 dst 内容。
//如果在安卓4.4或者之前的版本，在默认开启硬件加速的情况下，更改 dst 的内容后可能绘制会出现问题，请关闭硬件加速或者给 dst 添加一个单个操作，例如: dst.rLineTo(0, 0)

boolean getSegment (float startD, float stopD, Path dst, boolean startWithMoveTo)

```
* getPosTan

```java
/*这个方法是用于得到路径上某一长度的位置以及该位置的正切值：
参数    作用    备注
返回值(boolean)	判断获取是否成功	true表示成功，数据会存入 pos 和 tan 中，
false 表示失败，pos 和 tan 不会改变
distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
pos	该点的坐标值	坐标值: (x==[0], y==[1])
tan	该点的正切值	正切值: (x==[0], y==[1])
*/
boolean getPosTan (float distance, float[] pos, float[] tan)
```
* getMatrix

这个方法是用于得到路径上某一长度的位置以及该位置的正切值的矩阵：
```java
/*
返回值(boolean)    判断获取是否成功	true表示成功，数据会存入matrix中，false 失败，matrix内容不会改变
distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
matrix	根据 falgs 封装好的matrix	会根据 flags 的设置而存入不同的内容
flags	规定哪些内容会存入到matrix中	可选择
POSITION_MATRIX_FLAG(位置)
ANGENT_MATRIX_FLAG(正切)
其实这个方法就相当于我们在前一个例子中封装 matrix 的过程由 getMatrix 替我们做了，我们可以直接得到一个封装好到 matrix，岂不快哉。
*/
boolean getMatrix (float distance, Matrix matrix, int flags)
```