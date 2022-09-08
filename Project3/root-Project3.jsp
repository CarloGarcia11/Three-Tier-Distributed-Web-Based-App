<!DOCTYPE html>

<%-- start script --%>

<%
String clientQ = (String) session.getAttribute("sqlCommand");
String table = (String) session.getAttribute("table");
if(table == null) table = " ";
if(clientQ == null) clientQ = " ";
%>

<html>

<head>
    <title>Root-Level</title>
    <style type='text/css'>
        table,th,td{border-style: solid;}
        #sqlTable{ display: flex; justify-content: center  }
        .error{color:red ;}
        .update{color:green ;}
        </style>

  
</head>

<body>

    <!--This is the top version of the application-->
    <section style="justify-content: center; text-align: center;">
        <h1>You are connected to the Project 3 Database.</h1>
        <h2>A Servlet/JSP-Based Multi-Tiered Enterprise Application Using A Tomcat Container</h2>
        <hr>
        <p>
            <h3>You are connected to the Project 3 Enterprise System Database as a <span style="color:red ;">Root-Level</span> user.</h3>
            <h3>Please enter any valid SQL Query command in the box below.</h3>
        </p>
        <form action="/Project3/root1" method="post">
            <textarea name="sqlCommand" id="sqlBox" cols="70" rows="15"></textarea>
            <div id="buttonsList">
                <button type="submit">Execute Command</button>
                <button onClick="reset();" type="button">Reset Form </button>
                <button onClick="clearT();" type="button">Clear Results</button>
            </div>
        </form>

        <h3>All execution results will appear below this line. </h3>
        <hr>
    </section>
    <h2 style="justify-content: center; text-align: center;">Results</h2>
    <!--Below this point the SQL Table, Error Messages , Or Update Messages Should Appear-->
    <div id="sqlTable">  <%=table%>  </div> 


</body>

<script type="text/javascript">
    function clearT(){
     document.getElementById("data").remove();
    


}
</script>  

<script type="text/javascript">
    function reset() {
    document.getElementById('sqlBox').value = " ";
}  

</script> 

</html>