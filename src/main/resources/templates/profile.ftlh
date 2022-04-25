<#import "base.ftlh" as base>

<@base.layout>
    <h1>Profile</h1>
    <p>Logged user: ${user.email}</p>
    <p>To see information about another user please relogin</p>
    <div class="flex flex-col align-center">
        <h1>Books at hands</h1>
        <main class="flex flex-wrap align-center">
            <#list user.books as book>
                <div class="card">
                    <div class="flex flex-col align-center">
                        <#if book.imageString??>
                            <img alt="item picture" src="${'data:image/png;base64,'+book.imageString}"/>
                        </#if>
                        <p>Title: ${book.title}</p>
                        <p>Author: ${book.author}</p>
                        <a class="as-button" href="/return/${book.id}">Return</a>
                        <a class="as-button" href="/books/${book.id}">Details</a>
                    </div>
                </div>
            </#list>
        </main>
    </div>

</@base.layout>