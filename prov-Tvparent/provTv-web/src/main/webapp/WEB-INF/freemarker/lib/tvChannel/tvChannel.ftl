<#import "/plugin/tags.ftl" as tags />
<#macro findTv>
<script type="text/javascript">
    $.ajax({
        url: "<@tags.spring.url value="/tvList/findTvChannelList" />",
        type: "POST",
        dataType: "json",
        success: function (data) {
            var json = eval("("+data.result+")");
            var sb = "";
            var list = json;
            for (var i = 0; i < list.length; i++) {
                sb = sb + "<OPTION value="+list[i].freq+"_"+list[i].videoStandard+"_"+list[i].audioStandard+">"+list[i].chennalNum+"</OPTION>";
            }
            $("#t_detail").append(sb);
        }
    });
</script>
</#macro>

<#macro findPromptMsg>
<script type="text/javascript">
    $.ajax({
        url: "<@tags.spring.url value="/tvList/findPromptMsg" />",
        type: "POST",
        dataType: "json",
        success: function (data) {
            var json = data.result;
            var list = eval("("+json+")");
            var sb = "";
           for (var i = 0; i < list.length; i++) {
                   sb = sb + "<tr><td>"+list[i].chennalNum+"</td><td>"+list[i].channelName+"</td><td>"+list[i].freq+"</td></tr>";
            }
            $("#tvMatchTableBody").append(sb);
        }
    });
</script>
</#macro>