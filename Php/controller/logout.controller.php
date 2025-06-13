<?php

if(!auth()){
   header("Location: index");
}

session_unset();         

header("Location: index"); 
          
