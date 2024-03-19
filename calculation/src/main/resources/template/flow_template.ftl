<?xml version="1.0" encoding="UTF-8" ?>
<flow>
    <chain name="${flowName}">
        THEN(
        <#list levels as level>
            WHEN(
                <#list level.nodes as node>
                ${node}<#if node_has_next>,</#if>
                </#list>
            )<#if level_has_next>,</#if>
        </#list>
        )
    </chain>
</flow>
