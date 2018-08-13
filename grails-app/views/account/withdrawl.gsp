<html>
   <head>
     <title>Withdrawl</title>
     <meta name="layout" content="main" />
     <asset:stylesheet href="errors.css"/>

   </head>
   <body>
      <g:form controller="Account">
          ${g.message(code:'default.withdrawl.amount')}: $<g:textField name="amount" value="${0.00}" />
         <br/><br/>
         <g:actionSubmit value="${g.message(code:'default.continue')}" action="doWithdrawl" />
         <g:actionSubmit value="${g.message(code:'default.go.back')}" action="index" />
      </g:form>
      <br/><br/>
      <g:if test="${errorMsg}">
         <p class="error-details">${errorMsg}</p>
      </g:if>
   </body>
</html>
