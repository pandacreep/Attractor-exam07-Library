<#import "base.ftlh" as base>

<@base.layout>
    <h1>Information about the book</h1>
    <div class="row">
        <p>Id: <i>${book.id}</i></p>
        <p>Title: <i>${book.title}</i></p>
        <p>Author: <i>${book.author}</i></p>
        <p>Publication year: <i>${book.year}</i></p>
        <p>Description: <i>${book.description}</i></p>
        <p>Data of adding: <i>${book.dateAdd}</i></p>
        <#if book.imageString??>
            <img alt="item picture" src="${'data:image/png;base64,'+book.imageString}"/>
        </#if>
    </div>
</@base.layout>