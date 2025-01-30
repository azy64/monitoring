# LAW ORDER
LAW ORDER is a small API for handling court's process during a trial.
It's generally put together the lawyer of defense, the culprit, the judge and the Accuser.

Getting Started
---------------
1. ``` git clone https://github.com/azy64/law.order.git```

2. ``` cd law.order ```

3. ``` docker-compose up ```

4. ```docker exec -ti laworder_server-db_1 bash /home/create-user.sh ```


Checking
-------
5. 
    - ``` docker exec -ti laworder_server-db_1 bash ```

    - ``` mysql -u root -D mysql -p ```

    - ``` select user from mysql.user; ```

6. ``` docker exec -ti laworder_server-web_1 bash /home/law/apt-install.sh ```

7. Go to your host browser and type: http://localhost:8000/law/specialisation/, it should display: "Je suis la page Acceuil" 
