<#import "base.ftlh" as base>

<@base.layout>
    <div class="flex flex-col align-center">
        <h1>Books in the library</h1>
        <main class="flex flex-wrap align-center">
            <#list books as book>
                <div class="card">
                    <div class="flex flex-col align-center">
                        <#if book.imageString??>
                            <img alt="item picture" src="${'data:image/png;base64,'+book.imageString}"/>
                        </#if>
                        <p>Title: ${book.title}</p>
                        <p>Author: ${book.author}</p>
                        <#if book.available == true>
                            <a class="as-button" href="/get/${book.id}">Get</a>
                        </#if>
                        <a class="as-button" href="/books/${book.id}">Details</a>
                    </div>
                </div>
            </#list>
        </main>
        <p>
            <#if linkPrevious??>
                <a href="${linkPrevious}">Prev</a>
            </#if>
            <#if linkNext??>
                <a href="${linkNext}">Next</a>
            </#if>
        </p>
    </div>
</@base.layout>