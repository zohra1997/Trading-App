

<h1 id="trading-application">Trading Application</h1>
<h2 id="introduction">Introduction</h2>
<p>Trading app is an online stock trading simulation REST API which can be used to check stock market, create a user account, deposite and withdraw money, buy and sell stocks. The REST API can be utilized by front-end developers, mobile-app developers, and traders.<br>
It is a MicroService which is implememnted in Java with SpringBoot, PSQL database, IEX market Data and Http pooling connection manager.</p>
<h2 id="quick-start">Quick Start</h2>
<p>To utilize this REST API , CentOS 7, Docker and Java are required.</p>
<p><strong>Steps to start the API</strong></p>
<ol>
<li>
<p>Download the codes:<br>
<code>git clone https://github.com/zohra1997/Trading-App</code></p>
</li>
<li>
<p>Initialize The database:<br>
<code>systemctl status docker || systemctl start docker</code><br>
<code>export PSQL_PASSWORD="your password"</code><br>
<code>export PSQL_USER=" psql user"</code><br>
<code>export PSQL_HOST="psql host"</code><br>
<code># create docker voume to persist data</code><br>
<code>docker volume create pgdata</code><br>
<code>dcoker run --rm --name jrvs-psql -e POSTGRES_PASSWORD=$PSQL_PASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 $PSQL_USER</code><br>
<code># create the database and tables</code><br>
<code>psql -h $PSQL_HOST -U $PSQL_USER -f ./psql/sql_ddl/init_db.sql psql -h $PSQL_HOST -U $PSQL_USER -d jrvstrading -f ./psql/sql_ddl/schema.sql</code></p>
</li>
<li>
<p>Run spring boot app:<br>
<code>java -jar ./target/trading-1.0-SNAPSHOT.jar</code></p>
</li>
<li>
<p>To Access the API:<br>
Type the following in your browser or use postman to access the API .<br>
<code>https://http://localhost:8080/swagger-ui.html</code><br>
<img src="https://lh3.googleusercontent.com/9q0qfrwbb8ECy5RiUg_tb6eu147h1LN3bUH-pg3vF9CMe5kgW8jSa0BRhir_ZO7olpGrpv8XbmU" alt="enter image description here"><br>
<img src="https://lh3.googleusercontent.com/M6R7a5RnabFshO8ZFyaM8tk6n7p6ow0MocdiZKgMHe1Df2xWNrEQmnuhdgFXoDQoS9v8sLplNQc" alt="enter image description here"></p>
</li>
</ol>
<h2 id="rest-api-usage">REST API Usage</h2>
<h3 id="swagger">Swagger</h3>
<p>Swagger OpenApi is used to describe the structure REST API so that machines can read them,  including available endpoints, operations on each endpoint, and operation parameters. It has made the usage of the REST API very intuitive which benefits front-end developers to easily consume the microservice.</p>
<h3 id="qoute-controller">Qoute Controller</h3>
<p>This end point is used to connect to IexCloud and retreive data regarding specific quotes and save them to the local psql database.</p>
<ul>
<li>GET <code>/quote/iex/ticker/{ticker}</code>: accept a ticker as input and return aveilable information from IexCloud.</li>
<li>GET <code>/quote/tickerId/t{ickerId}</code>: accept a ticker Id as input, search IexCloud for data and store data in the local psql database.</li>
<li>GET <code>/quote/dailyList</code>: list all securities that are available to trading in this trading system</li>
<li>PUT <code>/quote/iexMarketData</code>: Update all quotes from IEX which is an external market data source</li>
<li>PUT <code>/quote/</code>: update a quote mannually in the database.</li>
</ul>
<h3 id="trader-controller">Trader Controller</h3>
<p>Trader Controller end point is used to store data about trader into the psql database. it can manage trader and account information, for example depositing or withdrawing fund from a given account.</p>
<ul>
<li>POST <code>/trader/</code>: Creates a trader account and store trader information with Dto.</li>
<li>POST<code>/trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country/email/{email}}</code>: create a trader account and store information about the trader.</li>
<li>PUT<code>/trader/deposit/accountId/{accountId}/amount/{amount}</code>: deposite the specified amount of funds to the account with accountId.</li>
<li>PUT<code>/trader/withdraw/accountId/{accountId}/amount/{amount}</code>: withdraw the specified amount of funds from the account with accountId.</li>
<li>DELETE<code>/trader/traderId/{traderId}</code>: delete the trader with the target ID.</li>
</ul>
<h3 id="order-controller">Order Controller</h3>
<p>Order Contorller end point is where stock exchange takes place. when a trader buys stocks, the positions in his/her profiles gets updated which is an indication of how many stocks the trader has.</p>
<ul>
<li>POST <code>/order/MarketOrder</code>: accept the trader Id, size and the ticker Id. If size is a positive Integer, the trader has anough moiney and there are enough stocks available then the trader can buy those stocks. If size is a negative number and the trader has enough stocks then the user can sell the stocks.</li>
</ul>
<h3 id="app-controller">App controller</h3>
<ul>
<li>GET <code>/health</code> to make sure SpringBoot app is up and running</li>
</ul>
<h3 id="dashboard-controller">Dashboard controller</h3>
<p>This end point is used to present trader account summary and portfolio.</p>
<ul>
<li>GET<code>/dashboard/portfolio/traderId/{traderId}</code>: returns portfolio of a trader.</li>
<li>GET<code>/dashboard/portfile/traderId/{traderId}</code>: returns portfile of a trader.</li>
</ul>
<h2 id="architecture">Architecture</h2>
<p><img src="https://lh3.googleusercontent.com/l8B77oRJdkmIWYqXFD4QnpzksarfpD1CH1m-VCbyRz9KPwWmW_Lz11r5Wex_PXncMjVmB3BL2AQ" alt="enter image description here"></p>
<ul>
<li>
<p>Controller : This layer’s  job is to tranlate incoming requests into outgoing responses. To do this, the controller should take request data and pass it into the Service layer.The service layer then returns data back to the controller layer to present which is mostly in form of Json for a REST API.</p>
</li>
<li>
<p>Service : This layer is where business logic is implemented, which seperates the controller from dao layer. service layer defines the application boundaries.</p>
</li>
<li>
<p>Dao : The Data Access Object (DAO) layer seperates service layer from the persistence layer using an abstract API.  The functionality of this API is to hide from the application all the complexities involved in performing CRUD operations in the underlying storage mechanism. This permits both layers to evolve separately without knowing anything about each other.</p>
</li>
<li>
<p>SpringBoot: webservlet/TomCat and IoC</p>
</li>
<li>
<p>PSQL and IEX</p>
</li>
</ul>
<h2 id="improvements">Improvements</h2>
<ol>
<li>This REST API could be improved by deploying it into a server.</li>
<li></li>
</ol>

