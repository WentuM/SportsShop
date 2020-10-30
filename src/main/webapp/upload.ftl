<#import "../../../../../OneDrive/Рабочий стол/demo-file-uploader/src/main/webapp/freemarker/base.ftl" as base>

<@base.main>
    <form action="${action}" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit">
    </form>
</@base.main>