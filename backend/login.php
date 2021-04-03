<?php
$posts = array('user', 'pass', 'first_name', 'last_name', 'new_user', 'new_pass', 'new_avatar');
$gets = array('action');
if (!isset($_POST[$posts[0]]) || !isset($_POST[$posts[1]]) || !isset($_GET[$gets[0]])) die("404 not found");
include("connect.php");

// FIRST
$actions = array('sign_up', 'sign_in', 'edit_profile', 'upload_avatar', 'delete_avatar');
$unknownError = "unknownError";
$incomInfo = "incomInfo";
$notFound = "notFound";
$alreadyExists = "alreadyExists";
$separator1 = "<separator>";
$welcome = "welcome";
$preAvatar = "avatars/";
$postAvatar = ".jpg";
$folCalc = "https://easys.uk/calculations/";
$ID;$FIRST_NAME;$LAST_NAME;$USER_TYPE;$AVATAR = NULL;

// FUNCTIONS
function random_filename($length, $directory = '', $extension = '') {
    $dir = !empty($directory) && is_dir($directory) ? $directory : (dirname(__FILE__) . '/');
    do {
        $key = '';
        $keys = array_merge(range(0, 9), range('a', 'z'));
        for ($i = 0; $i < $length; $i++) $key .= $keys[array_rand($keys)];
    } while (file_exists($dir . $key . (!empty($extension) ? '.' . $extension : '')));
    return $key;// . (!empty($extension) ? '.' . $extension : '')
}



// SIGN UP
if ($_GET[$gets[0]] == $actions[0]) {
	$look = $c->query("SELECT * FROM users WHERE user='".$_POST[$posts[0]]."' LIMIT 1");
	if (!$look) dai($unknownError); 
	if ($look->num_rows > 0) dai($alreadyExists);
	else $c->query("INSERT INTO users (user, pass, first_name, last_name, joined_at) 
VALUES ('".$_POST[$posts[0]]."', '".$_POST[$posts[1]]."', '".$_POST[$posts[2]]."', '".$_POST[$posts[3]]."', '".time()."')");
}

// LOGIN
$login = $c->query("SELECT * FROM users WHERE user='".$_POST[$posts[0]]."' AND pass='".$_POST[$posts[1]]."' LIMIT 1");
if (!$login) dai($unknownError); 
if ($login->num_rows <= 0) dai($notFound);
else while($row = $login->fetch_assoc()) {// SIGN IN
	$ID = $row['id'];
	$FIRST_NAME = $row['first_name'];
	$LAST_NAME = $row['last_name'];
	$USER_TYPE = $row['type'];
	$AVATAR = $row['avatar'];
}
$c->query("UPDATE users SET last_login='".time()."' WHERE id='".$ID."'");


switch($_GET[$gets[0]]) {
	case $actions[0]:// SIGN UP
	    echo $welcome . $ID;
		break;
	case $actions[1]:// SIGN IN
	    echo $welcome . $ID . $separator1 . $FIRST_NAME . $separator1 . $LAST_NAME . $separator1 . $USER_TYPE . $separator1 . 
		        ($AVATAR != NULL ? $folCalc.$preAvatar.$AVATAR.$postAvatar : "");
		break;
	
	case $actions[2]:// EDIT PROFILE
	    if (isset($_POST[$posts[2]])) $c->query("UPDATE users SET first_name='".$_POST[$posts[2]]."' WHERE id='".$ID."'");
		if (isset($_POST[$posts[3]])) $c->query("UPDATE users SET last_name='".$_POST[$posts[3]]."' WHERE id='".$ID."'");
		if (isset($_POST[$posts[4]])) $c->query("UPDATE users SET user='".$_POST[$posts[4]]."' WHERE id='".$ID."'");
		if (isset($_POST[$posts[5]])) $c->query("UPDATE users SET pass='".$_POST[$posts[5]]."' WHERE id='".$ID."'");
		break;
		
	case $actions[3]:// UPLOAD AVATAR
		if (!isset($_POST[$posts[6]])) dai($incomInfo);
		if ($AVATAR != NULL) try {
			$perv = $preAvatar.$AVATAR.$postAvatar;
			if (file_exists($perv)) unlink($perv);
		} catch (Exception $e) {
		}
		try {
			$data = base64_decode($_POST[$posts[6]]);
			$im = imagecreatefromstring($data);
			if ($im !== false) {
				header('Content-Type: image/jpg');// file type
				$filename = random_filename(40, $preAvatar, "jpg");
				imagejpeg($im, $preAvatar.$filename.$postAvatar);// save as "filename.*"
				imagedestroy($im);// free up memory
				$c->query("UPDATE users SET avatar='".$filename."' WHERE id='".$ID."'");
				echo $welcome . $folCalc . $preAvatar.$filename.$postAvatar;
			} else echo $unknownError;
		} catch (Exception $e) {
			dai($unknownError);
		}
	    break;
		
	case $actions[4]:// REMOVE AVATAR
	    $perv = $preAvatar.$AVATAR.$postAvatar;
		try {
			if (file_exists($perv)) {
				unlink($perv);
				$c->query("UPDATE users SET avatar=NULL WHERE id='".$ID."'");
				echo $welcome;
			}
		} catch (Exception $e) {
			dai($unknownError);
		}
	    break;
		
	default:
		echo "404 not found";
}
$c->close();
?>
