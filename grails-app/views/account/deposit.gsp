<html>
   <head>
      <title>Deposit</title>
      <meta name="layout" content="main" />
   </head>
   <body>
      <g:form controller="Account">
         ${g.message(code:'default.deposit.amount')}: $<g:textField name="amount" value="${0.00}" />
         <br/><br/>
         <g:actionSubmit value="${g.message(code:'default.continue')}" action="doDeposit" />
         <g:actionSubmit value="${g.message(code:'default.go.back')}" action="index" />
      </g:form>
   </body>
</html>

