<#import "base.ftlh" as base>

<@base.layout>
    <h1>List of taken list only</h1>
    <#list books as k, value>
        Book <b>${k}</b> was taken by ${value.email} (${value.nameFirst} ${value.nameLast})
        <hr>
    </#list>
</@base.layout>