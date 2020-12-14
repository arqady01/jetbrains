# jetbrains
this is cangku


## 把这多个Sheet页合并到一个Sheet页的多行Sub 合并当前工作簿下的所有工作表()
1、在“汇总工作簿”中，新建一个sheet页。
2、在新建的sheet标签上单击右键，选择“查看代码”
3、在打开的VBA编辑窗口中粘贴以下代码：
Application.ScreenUpdating = False
For j = 1 To Sheets.Count
If Sheets(j).Name <> ActiveSheet.Name Then
X = Range("A65536").End(xlUp).Row + 1
Sheets(j).UsedRange.Copy Cells(X, 1)
End If
Next
Range("B1").Select
Application.ScreenUpdating = True
MsgBox "当前工作簿下的全部工作表已经合并完毕！", vbInformation, "提示"
End Sub
4、关闭该VBA编辑窗口
5、在Excel中，开发工具---宏,选“合并当前工作簿下的所有工作表”,然后“执行”。
