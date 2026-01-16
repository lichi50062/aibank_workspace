#!/usr/bin/expect -f
set username [lindex $argv 0];
set password [lindex $argv 1];
spawn gcloud anthos auth login --cluster cldatu1t
expect "*:"
send "$username\r"
expect "*:"
send "$password\r"
expect "success."