<%--
  Created by IntelliJ IDEA.
  User: P3700983
  Date: 10/25/2018
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:p="http://primefaces.org/ui">

<h:head>
</h:head>

<h:body>
  <p:spinner />
    <p:button outcome="productDetail" value="Bookmark" icon="ui-icon-star" disabled="true">
        <f:param name="productId" value="40" />
    </p:button>
</h:body>
</html>
