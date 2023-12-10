/**
 * ユーザー一覧画面
 */
// DOMの読み込み完了後に処理を開始
document.addEventListener('DOMContentLoaded', () => {
	const rows = document.querySelectorAll('#userList tbody tr');
	const updateBtn = document.getElementById('updateBtn');
	const deleteDummyBtn = document.getElementById('deleteDummyBtn');
	const deleteOkBtn = document.getElementById('deleteOkBtn');
	const deleteBtn = document.getElementById('deleteBtn');
	const selectedLoginId = document.getElementById('selectedLoginId');

	rows.forEach((row) => {
			// 行に対するクリックイベントの設定
			row.addEventListener('click', () => {
					// すべての行の選択状態を解除
					rows.forEach((otherRow) => otherRow.classList.remove('table-active'));
					// 選択された行を選択状態にする(class属性にtable-activeを追加)
					row.classList.add('table-active');
					// 更新ボタン、削除ボタンを活性化(disabled属性を削除)
					updateBtn.removeAttribute('disabled');
					deleteDummyBtn.removeAttribute('disabled');
					// ログインID一時保管
					editSelectedLoginId(row);
			});
	});


	// 削除処理サブミット
	deleteOkBtn.addEventListener('click', () => {
		// ダイアログの削除ボタン押下時にフォーム内のhidden削除ボタンを押下してsubmit
		deleteBtn.click();
	});


	/**
	 * 選択された行のログインIDを一時保管する
	 * 
	 * @param row 選択された行
	 */
	function editSelectedLoginId(row) {
			const cells = row.querySelectorAll('td');
			cells.forEach((cell) => {
					// 行の各セルに対して処理
					const columnId = cell.getAttribute('id');
					// id属性がloginId_で始まる要素の場合
					if (columnId.startsWith('loginId_')) {
							selectedLoginId.value = cell.textContent;
							return;
					}
			});
	}
});