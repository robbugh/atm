<html>
    <head>
        <title>Account Balance</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    ${g.message(code:'default.balance.is')} $${balance}
      <br/><br/>
      <g:form controller="Account">
         <g:actionSubmit value="${g.message(code:'default.go.back')}" action="index" />
      </g:form>
   </body>
</html>   
