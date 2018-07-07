<?php

$db = new PDO("mysql:host=sql12.freemysqlhosting.net;dbname=sql12246298","sql12246298","passwordnyadisini");

if (isset($_POST['idmessage'])){

	$idmessage		= $_POST['idmessage'];
	$message 		= $_POST['message'];

	$statement = $db->prepare("insert into notif values (:idmessage,:message)");
	$statement->bindValue(":idmessage",$idmessage);
	$statement->bindValue(":message",$message);


	if ($statment->execute()) { 
		$datas = array('result' => true);
	} else {
		$datas = array('result' => false);
	}

}else{
	$statement = $db->prepare("SELECT * FROM notif ORDER BY id_notif DESC LIMIT 1;");

	$statement->execute();
	$list_array = $statement->fetchAll(PDO::FETCH_OBJ);
	$dbdata = array();

	foreach ($list_array as $key => $value) {
		$dbdata[] = $value; 
	}
	$jsot = json_encode($list_array[0]);
	echo $jsot;
}

?>