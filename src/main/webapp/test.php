<?php
echo '<?xml version="1.0" encoding="UTF-8"?>'
echo '<Response>'
echo '<Say language="en-US" voice="woman"> '
echo 'This is the AMS System. There is an active Muster. Please report your status. </Say>'
echo '<Say language="en-US" voice="alice"> Press One for AT WORK </Say>'
echo '<Say language="en-US" voice="alice"> Press Two for AT HOME </Say>'
echo '<Say language="en-US" voice="alice"> Press Three for IN TRANSIT </Say>'
echo '<Say language="en-US" voice="alice"> Press Four for OTHER </Say>'
echo' <Pause length="5"/>'
echo '<Gather finishOnKey="#" timeout="10">'
echo '<Say language="en-US" voice="alice"> Please enter your muster status. </Say>'
echo '</Gather>'
echo '<Say language="en-US" voice="alice">Sorry, I didnt get your response.</Say>'
echo '<Redirect method="GET">https://raw.githubusercontent.com/IngramF/Project/master/twilioscript.xml</Redirect>'
echo '</Response>'
?>


