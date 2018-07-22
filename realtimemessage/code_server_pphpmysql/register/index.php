<?php 
try{
	$opt = array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION);
	$db = new PDO("mysql:host=localhost;dbname=db_sendmessage","root","",$opt);
	if (isset($_POST['token'])){
		$token		= $_POST['token'];

		$statement = $db->prepare("insert into pengguna (token) values (:token)");
		$statement->bindValue(":token",$token);

		if ($statement->execute()) { 
			$datas = array('result' => true);
		} else {
			$datas = array('result' => false);
		}
	}
}catch(PDOException $e){
	echo $e;
}
// if (isset($_POST["token"])) {
// 	$varToken=$_POST["token"];
// 	$conn = mysqli_connect("localhost","root","","db_sendmessage") or die("Error connecting");
// 	$query="INSERT INTO pengguna (token) VALUES ('$varToken') ON DUPLICATE KEY UPDATE token = '$varToken';";
// 	mysqli_query($conn,$query) or die(mysqli_error($conn));
// 	mysqli_close($conn);
// }else{
// 	echo "string";
// }

?>