<html>
   <head>
     <title>Spanning ATM</title>
     <meta name="layout" content="main" />
   </head>
   <body>
      <g:form controller="Account">
         <g:actionSubmit value="Account Balance" action="balance" />
         <g:actionSubmit value="Deposit" action="deposit" />
         <g:actionSubmit value="Withdrawl" action="withdrawl" />
      </g:form>
      <br/><br/>
      <g:form controller="Logout">
         <g:actionSubmit value="Logout" action="index" />
      </g:form>
   </body>
</html>
      
