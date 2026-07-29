select * from users where username = 'alekz';
select * from verifications where user_id = (select max(id) from users where username = 'alekz');