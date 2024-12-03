<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <autok>
            <xsl:for-each select="autok/auto">
                <xsl:sort select="ar" data-type="number" order="ascending"/>
                <auto>
                    <rsz><xsl:value-of select="@rsz"/></rsz>
                    <ar><xsl:value-of select="ar"/></ar>
                </auto>
            </xsl:for-each>
        </autok>
    </xsl:template>
</xsl:stylesheet>
