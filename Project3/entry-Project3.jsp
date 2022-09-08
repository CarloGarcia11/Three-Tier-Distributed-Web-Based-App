<!DOCTYPE html>

<%-- start script --%>

<%
String snumE = (String)session.getAttribute("snum");
String pnumE = (String)session.getAttribute("pnum");
String jnumE = (String)session.getAttribute("jnum");
String quantityE = (String)session.getAttribute("quantity");

String table = (String) session.getAttribute("table");
if(table == null) table = " ";

if(snumE == null) snumE = " ";
if(pnumE == null) pnumE = " ";
if(jnumE == null) jnumE = " ";
if(quantityE == null) quantityE = " ";
%>
<html>

<head>
    <title>Data Entry-Level</title>

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
            <h3>You are connected to the Project 3 Enterprise System Database as a <span style="color:red ;">Data Entry-Level</span> user.</h3>
            <h3>Please enter the data values in the form below to add a new record to the shipments table.</h3>
        </p>
        <form method= "post" action="/Project3/entry1">
           
            <div style="display: flex; justify-content: center;" >
                <table >
                                <tr>
                                    <th> <label for="snum">snum</label> </th>
                                    <th> <label for="pnum">pnum</label></th>
                                    <th>  <label for="jnum">jnum</label> </th>
                                    <th> <label for="snum">quantity</label>  </th>
                                </tr>

                                <tr>
                                    <td>  <input type="text" name="snum">  </td>
                                    <td>  <input type="text" name="pnum" > </td>
                                    <td>   <input type="text" name="jnum" >  </td>
                                    <td>   <input type="text" name="quantity" > </td>

                                </tr>
                            </table>

            </div>
            
               
            <div id="buttonsList">
                <button type="submit">Enter Record Into Database </button>
                <button button onClick="clearT();" type="button">Clear Results</button>
            </div>
        </form>

    
        <hr>
        <h3> Database Results: </h3>
    </section>
    <!--Below this point the SQL Table, Error Messages , Or Update Messages Should Appear-->
    <div id="sqlTable">  <%=table%>  </div> 


</body>

<script type="text/javascript">
    function clearT(){
     document.getElementById("data").remove();
}
</script>  

</html>