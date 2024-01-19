# AttendanceManage

## 作業分担

宮越：CSS<br>
槇野：CSS<br>
吉本：ユーザ更新<br>
森田：履歴一覧<br>
垣根：勤怠入力<br>
重本：連絡先登録<br>

## DBの準備
### usersテーブル
<ol>
  <li>DDLフォルダにあるusers.sqlのcreate文を実行し、データベースにusersテーブルを作成する。</li>
  <li>DMLフォルダにあるusers.sqlのinsert文を実行し、テストデータを作成する。<br></li>
</ol>
<table>
  <tr>
    <th>権限</th><th>ユーザID</th><th>パスワード</th>
  </tr>
  <tr>
    <td>一般</td><td>90001</td><td>1234</td>
  </tr>
  <tr>
    <td>管理者</td><td>90002</td><td>12345</td>
  </tr>
</table>

### attendancesテーブル
<ol>
  <li>usersテーブルと同じ手順でテーブルとテストデータを作成してください。</li>
</ol>

## 進捗
<table>
  <tr>
    <th>必須機能要件</th>
    <th>機能作成</th>
    <th>テスト作成</th>
  </tr>
  <tr>
    <td>ユーザー名,パスワードでログインできる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>ログアウトできる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>出勤ボタンを押して出勤時刻を記録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>退勤ボタンを押して退勤時刻を記録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>休憩開始ボタンを押して休憩開始時刻を記録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>休憩終了ボタンを押して休憩終了時刻を記録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>勤怠履歴一覧を表示できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>連絡先を登録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>連絡先を編集できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>勤務場所を登録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>勤怠状況一覧を表示できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>CSS</td>
    <td>完了</td>
    <td></td>
  </tr>
  <tr>
    <th>追加機能要件 〜admin権限〜</th>
    <th>機能作成</th>
    <th>テスト作成</th>
  </tr>
  <tr>
    <td>ユーザー一覧を表示できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>ユーザーを新規登録できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>ユーザーを削除できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>ユーザーを更新できる</td>
    <td>完了</td>
    <td>未</td>
  </tr>
  <tr>
    <td>CSS</td>
    <td>完了</td>
    <td></td>
  </tr>
</table>


