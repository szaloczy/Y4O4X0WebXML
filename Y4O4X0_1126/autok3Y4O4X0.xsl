<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Dokumentum elemeinek száma</h2>
                <p>
                    <xsl:value-of select="count(//*)"/>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
