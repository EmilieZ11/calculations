<?php
$posts = array('user', 'pass', 'incomes', 'expenses', 'banks', 'chests', 'cashes', 'contacts', 'relatives', 'subjects', 'projects', 'debts', 'credits', 'last_sync', 'now', 'upload_type');
$cancellation = "404 not found";
if (!isset($_POST[$posts[0]]) || !isset($_POST[$posts[1]]) || !isset($_POST[$posts[15]])) die($cancellation);
include("connect.php");

// LOGIN
$ID;$ServerLS = 0;
$login = $c->query("SELECT * FROM users WHERE user='".$_POST[$posts[0]]."' AND pass='".$_POST[$posts[1]]."' LIMIT 1");
if (!$login) dai($unknownError); 
if ($login->num_rows <= 0) dai($notFound);
else while($row = $login->fetch_assoc()) {
	$ID = $row['id'];
	$ServerLS = intval($row['last_sync']);
}

// FIRST
$DeviceLS = 0;
if (isset($_POST[$posts[13]])) $DeviceLS = intval($_POST[$posts[13]]);
$RECREATE = array(); $DELETE = array(); $UPDATE = array(); $INSERT = array(); $REQUEST = array();
$tables = array("incomes", "expenses", "banks", "chests", "cashes", "contacts", "relatives", "subjects", "projects", "debts", "credits");
$dbId = "item_id"; $dbDateModified = "date_modified";
function insertion($c, $w, $userId, $itemId, $tables) {
	$i = array();
	$i["type"] = $w;
	$i["id"] = $itemId;
	$get = $c->query("SELECT * FROM ".$tables[$w]." WHERE user_id='".$userId."' AND item_id='".$itemId."' LIMIT 1");
	if ($get->num_rows > 0) while($r = $get->fetch_assoc()) {
		switch($w) {
			case 0:// INCOMES
			case 1:// EXPENSES
			    $i["date"] = $r["date"];
				$i["amount"] = $r["amount"];
				$i["currency"] = $r["currency"];
				$i["subjectId"] = $r["subject_id"];
				$i["contactId"] = $r["contact_id"];
				$i["treasuryType"] = $r["treasury_type"];
				$i["treasuryId"] = $r["treasury_id"];
				$i["behalf"] = $r["behalf"];
				$i["isCheque"] = $r["is_cheque"];
				$i["attachment"] = $r["attachment"];
				$i["projectId"] = $r["project_id"];
			    break;
			case 2:// BANKS
			    $i["name"] = $r["name"];
				$i["accountNumber"] = $r["account_number"];
				$i["cardNumber"] = $r["card_number"];
				$i["initial"] = $r["initial"];
				$i["initialCurrency"] = $r["initial_currency"];
				$i["dateFounded"] = $r["date_founded"];
				$i["yearExpired"] = $r["year_expired"];
				$i["monthExpired"] = $r["month_expired"];
				$i["colour"] = $r["colour"];
				break;
			case 3:// CHESTS
			    $i["name"] = $r["name"];
				$i["subscriptionCode"] = $r["subscription_code"];
				$i["initial"] = $r["initial"];
				$i["initialCurrency"] = $r["initial_currency"];
				$i["dateFounded"] = $r["date_founded"];
				break;
			case 4:// CASHES
				$i["initial"] = $r["initial"];
				$i["initialCurrency"] = $r["initial_currency"];
				$i["dateEarned"] = $r["date_earned"];
				break;
			case 5:// CONTACTS
			    $i["firstName"] = $r["first_name"];
				$i["lastName"] = $r["last_name"];
				$i["phone"] = $r["phone"];
				$i["email"] = $r["email"];
				$i["accountNumber"] = $r["account_number"];
				$i["cardNumber"] = $r["card_number"];
				break;
			case 6:// RELATIVES
			    $i["firstName"] = $r["first_name"];
				$i["lastName"] = $r["last_name"];
				$i["relativity"] = $r["relativity"];
				$i["phone"] = $r["phone"];
				break;
			case 7:// SUBJECTS
			    $i["name"] = $r["name"];
				$i["repetition"] = $r["repetition"];
				$i["forCompany"] = $r["for_company"];
				break;
			case 8:// PROJECTS
		    	$i["name"] = $r["name"];
				$i["dateStarted"] = $r["date_started"];
				$i["dateEnded"] = $r["date_ended"];
				$i["contactId"] = $r["contact_id"];
				break;
			case 9:// DEBTS
			case 10:// CREDITS
				$i["amount"] = $r["amount"];
				$i["currency"] = $r["currency"];
				$i["subjectId"] = $r["subject_id"];
				$i["contactId"] = $r["contact_id"];
				$i["repaymentDate"] = $r["repayment_date"];
				$i["treasuryType"] = $r["treasury_type"];
				$i["treasuryId"] = $r["treasury_id"];
				$i["behalf"] = $r["behalf"];
				$i["notify"] = $r["notify"];
				break;
		}
		$i["notes"] = $r["notes"];
		$i["dateCreated"] = $r["date_created"];
		$i["dateModified"] = $r["date_modified"];
	}
	return $i;
}
$UPBRIEF = "brief"; $UPLONG = "long";


// BRIEF SYNC
if ($_POST[$posts[15]] == $UPBRIEF) {
	for ($t = 0; $t < 9; $t++) if (isset($_POST[$posts[$t + 2]])) {//11
	    $j = json_decode($_POST[$posts[$t + 2]]);
		$findAll = $c->query("SELECT ".$dbId.", ".$dbDateModified." FROM ".$tables[$t]." WHERE user_id='".$ID."'");
	    $all = array(); $LMs = array();
		if ($findAll->num_rows > 0) while($row = $findAll->fetch_assoc()) {
			array_push($all, intval($row[$dbId]));
			array_push($LMs, intval($row[$dbDateModified]));
		}
		foreach ($j as $i) {
			$report = array($t, $i[0]);
			$find = $c->query("SELECT * FROM ".$tables[$t]." WHERE user_id='".$ID."' AND item_id='".strval($i[0])."' LIMIT 1");
			if ($find->num_rows == 0) {
				if ($DeviceLS == 0) array_push($RECREATE, $report);
				else if (intval($i[1]) > $ServerLS) array_push($REQUEST, $report);
				else array_push($DELETE, $report);
			} else {
				$lastModified = 0;
				while($row = $find->fetch_assoc()) {
					$lastModified = intval($row['date_modified']);
					$all[array_search(intval($row['id']), $all)] = -1;
				}
				if ($lastModified < $i[1]) array_push($REQUEST, $report);
				else if ($lastModified > $i[1])
					array_push($UPDATE, insertion($c, $t, $ID, $i[0], $tables));
			}
		}
		for ($d = 0; $d < count($all); $d++) if ($all[$d] != -1) {
			if ($DeviceLS > $LMs[$d]) $c->query("DELETE FROM ".$tables[$t]." WHERE id='".$all[$d]."'");
			else array_push($INSERT, insertion($c, $t, $ID, $all[$d], $tables));
		}
	}
	
	$NOW;
	if (isset($_POST[$posts[14]])) $NOW = $_POST[$posts[14]]; else $NOW = time();
	$c->query("UPDATE users SET last_sync='".$NOW."' WHERE id='".$ID."'");
	
	die("done".json_encode(array(
	        "REQUEST"=>$REQUEST,
			"RECREATE"=>$RECREATE,
			"DELETE"=>$DELETE,
			"UPDATE"=>$UPDATE,
			"INSERT"=>$INSERT
		), JSON_UNESCAPED_UNICODE));
}



// LONG SYNC
if ($_POST[$posts[15]] != $UPLONG) die($cancellation);

// INCOMES
if (isset($_POST[$posts[2]])) foreach (json_decode($_POST[$posts[2]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[0]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	$isCheque = 0;
	if (strval($i->isCheque) == "true") $isCheque = 1;
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[0]." (user_id, item_id, date, amount, currency, subject_id, 
	    contact_id, treasury_type, treasury_id, behalf, is_cheque, attachment, project_id, notes, date_created, date_modified) 
		VALUES ('".$ID."', '".strval($i->id)."', '".strval($i->date)."', '".strval($i->amount)."', '".strval($i->currency)."', 
		'".strval($i->subjectId)."', '".strval($i->contactId)."', '".strval($i->treasuryType)."', '".strval($i->treasuryId)."', 
		'".strval($i->behalf)."', '".$isCheque."', '".strval($i->attachment)."', '".strval($i->projectId)."', '".strval($i->notes)."', 
		'".strval($i->dateCreated)."', '".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[0]." SET date='".strval($i->date)."', amount='".strval($i->amount)."', 
		currency='".strval($i->currency)."', subject_id='".strval($i->subjectId)."', contact_id='".strval($i->contactId)."', 
		treasury_type='".strval($i->treasuryType)."', treasury_id='".strval($i->treasuryId)."', behalf='".strval($i->behalf)."', 
		is_cheque='".$isCheque."', attachment='".strval($i->attachment)."', project_id='".strval($i->projectId)."', 
		notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' WHERE user_id='".$ID."' AND 
		item_id='".strval($i->id)."'");
}

// EXPENSES
if (isset($_POST[$posts[3]])) foreach (json_decode($_POST[$posts[3]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[1]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	$isCheque = 0;
	if (strval($i->isCheque) == true) $isCheque = 1;
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[1]." (user_id, item_id, date, amount, currency, subject_id, contact_id, 
	    treasury_type, treasury_id, behalf, is_cheque, attachment, project_id, notes, date_created, date_modified) VALUES ('".$ID."', 
		'".strval($i->id)."', '".strval($i->date)."', '".strval($i->amount)."', '".strval($i->currency)."', 
		'".strval($i->subjectId)."', '".strval($i->contactId)."', '".strval($i->treasuryType)."', '".strval($i->treasuryId)."', 
		'".strval($i->behalf)."', '".$isCheque."', '".strval($i->attachment)."', '".strval($i->projectId)."', '".strval($i->notes)."', 
		'".strval($i->dateCreated)."', '".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[1]." SET date='".strval($i->date)."', amount='".strval($i->amount)."', 
		currency='".strval($i->currency)."', subject_id='".strval($i->subjectId)."', contact_id='".strval($i->contactId)."', 
		treasury_type='".strval($i->treasuryType)."', treasury_id='".strval($i->treasuryId)."', behalf='".strval($i->behalf)."', 
		is_cheque='".$isCheque."', attachment='".strval($i->attachment)."', project_id='".strval($i->projectId)."', 
		notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' WHERE user_id='".$ID."' AND 
		item_id='".strval($i->id)."'");
}

// BANKS
if (isset($_POST[$posts[4]])) foreach (json_decode($_POST[$posts[4]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[2]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[2]." (user_id, item_id, name, account_number, card_number, initial, 
		initial_currency, date_founded, year_expired, month_expired, colour, notes, date_created, date_modified) VALUES ('".$ID."', 
		'".strval($i->id)."', '".strval($i->name)."', '".strval($i->accountNumber)."', '".strval($i->cardNumber)."', 
		'".strval($i->initial)."', '".strval($i->initialCurrency)."', '".strval($i->dateFounded)."', 
		'".strval($i->yearExpired)."', '".strval($i->monthExpired)."', '".strval($i->colour)."', '".strval($i->notes)."', 
		'".strval($i->dateCreated)."', '".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[2]." SET name='".strval($i->name)."', account_number='".strval($i->accountNumber)."', 
		card_number='".strval($i->cardNumber)."', initial='".strval($i->initial)."', 
		initial_currency='".strval($i->initialCurrency)."', date_founded='".strval($i->dateFounded)."', 
		year_expired='".strval($i->yearExpired)."', month_expired='".strval($i->monthExpired)."', colour='".strval($i->colour)."',
		notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' 
		WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

// CHESTS
if (isset($_POST[$posts[5]])) foreach (json_decode($_POST[$posts[5]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[3]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[3]." (user_id, item_id, name, subscription_code, initial, initial_currency, 
	    date_founded, notes, date_created, date_modified) VALUES ('".$ID."', '".strval($i->id)."', '".strval($i->name)."', 
		'".strval($i->subscriptionCode)."', '".strval($i->initial)."', '".strval($i->initialCurrency)."', 
		'".strval($i->dateFounded)."', '".strval($i->notes)."', '".strval($i->dateCreated)."', '".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[3]." SET name='".strval($i->name)."', subscription_code='".strval($i->subscriptionCode)."', 
		initial='".strval($i->initial)."', initial_currency='".strval($i->initialCurrency)."', 
		date_founded='".strval($i->dateFounded)."', notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' 
		WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

// CASHES
if (isset($_POST[$posts[6]])) foreach (json_decode($_POST[$posts[6]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[4]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[4]." (user_id, item_id, initial, initial_currency, date_earned, notes, 
	    date_created, date_modified) VALUES ('".$ID."', '".strval($i->id)."', '".strval($i->initial)."', '".strval($i->initialCurrency)."', 
		'".strval($i->dateEarned)."', '".strval($i->notes)."', '".strval($i->dateCreated)."', '".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[4]." SET initial='".strval($i->initial)."', initial_currency='".strval($i->initialCurrency)."', 
		date_earned='".strval($i->dateEarned)."', notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' 
		WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

// CONTACTS
if (isset($_POST[$posts[7]])) foreach (json_decode($_POST[$posts[7]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[5]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[5]." (user_id, item_id, first_name, last_name, phone, email, 
	    account_number, card_number, notes, date_created, date_modified) VALUES ('".$ID."', '".strval($i->id)."', 
		'".strval($i->firstName)."', '".strval($i->lastName)."', '".strval($i->phone)."', '".strval($i->email)."', 
		'".strval($i->accountNumber)."', '".strval($i->cardNumber)."', '".strval($i->notes)."', '".strval($i->dateCreated)."', 
		'".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[5]." SET first_name='".strval($i->firstName)."', last_name='".strval($i->lastName)."', 
		phone='".strval($i->phone)."', email='".strval($i->email)."', account_number='".strval($i->accountNumber)."', 
		card_number='".strval($i->cardNumber)."', notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' 
		WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

// RELATIVES
if (isset($_POST[$posts[8]])) foreach (json_decode($_POST[$posts[8]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[6]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[6]." (user_id, item_id, first_name, last_name, relativity, phone, notes, 
	    date_created, date_modified) VALUES ('".$ID."', '".strval($i->id)."', '".strval($i->firstName)."', '".strval($i->lastName)."', 
		'".strval($i->relativity)."', '".strval($i->phone)."', '".strval($i->notes)."', '".strval($i->dateCreated)."', 
		'".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[6]." SET first_name='".strval($i->firstName)."', last_name='".strval($i->lastName)."', 
		relativity='".strval($i->relativity)."', phone='".strval($i->phone)."', notes='".strval($i->notes)."', 
		date_modified='".strval($i->dateModified)."' WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

// SUBJECTS
if (isset($_POST[$posts[9]])) foreach (json_decode($_POST[$posts[9]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[7]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	$forCompany = 0;
	if (strval($i->forCompany) == "true") $forCompany = 1;
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[7]." (user_id, item_id, name, repetition, for_company, notes, date_created, 
	    date_modified) VALUES ('".$ID."', '".strval($i->id)."', '".strval($i->name)."', '".strval($i->repetition)."', '".$forCompany."', 
		'".strval($i->notes)."', '".strval($i->dateCreated)."', '".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[7]." SET name='".strval($i->name)."', repetition='".strval($i->repetition)."', 
		for_company='".$forCompany."', notes='".strval($i->notes)."', date_modified='".strval($i->dateModified)."' 
		WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

// PROJECTS
if (isset($_POST[$posts[10]])) foreach (json_decode($_POST[$posts[10]]) as $i) {
	$find = $c->query("SELECT * FROM ".$tables[8]." WHERE user_id='".$ID."' AND item_id='".strval($i->id)."' LIMIT 1");
	if ($find->num_rows == 0) $c->query("INSERT INTO ".$tables[8]." (user_id, item_id, name, date_started, date_ended, contact_id, notes, 
	    date_created, date_modified) VALUES ('".$ID."', '".strval($i->id)."', '".strval($i->name)."', '".strval($i->dateStarted)."', 
		'".strval($i->dateEnded)."', '".strval($i->contactId)."', '".strval($i->notes)."', '".strval($i->dateCreated)."', 
		'".strval($i->dateModified)."')");
	else $c->query("UPDATE ".$tables[8]." SET name='".strval($i->name)."', date_started='".strval($i->dateStarted)."', 
		date_ended='".strval($i->dateEnded)."', contact_id='".strval($i->contactId)."', notes='".strval($i->notes)."', 
		date_modified='".strval($i->dateModified)."' WHERE user_id='".$ID."' AND item_id='".strval($i->id)."'");
}

?>
