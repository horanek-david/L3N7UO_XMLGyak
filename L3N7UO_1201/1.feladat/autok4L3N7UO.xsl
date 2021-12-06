<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
			<h4>Miskolci autok</h4>
				<ul>
					<xsl:for-each select = "autok/auto">
					        <xsl:if test="tulaj/varos='Miskolc'">
			                    <li>
			                    	<xsl:value-of select="tulaj/nev"/>
			                    	<p></p>
			                    	<xsl:value-of select="@rsz"/>
			                    	<p></p>
			                    </li>
					        </xsl:if>
					</xsl:for-each>
				</ul>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>