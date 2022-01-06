<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="author">
		<xsl:value-of select="."/>
		<xsl:if test="position()!=last()">,</xsl:if>
	</xsl:template>
	<xsl:template match="/">
		<html>
			<head>
				<title/>
			</head>
			<body>
				<h1 align="center">图书信息</h1>
			</body>
			<table border="3" cellpadding="2" cellspacing="6" align="center">
				<tbody>
					<tr>
						<th>id</th>
						<th>商品类型</th>
						<th>ISBN编号</th>
						<th>作者</th>
						<th>书名</th>
						<th>出版社</th>
						<th>价格</th>
					</tr>
				</tbody>
				<xsl:for-each select="booklist/book">
					<xsl:choose>
						<xsl:when test="position() mod 2!=1">
							<tr bgcolor="white">
								<td>
									<xsl:value-of select="@id"/>
								</td>
								<td>
									<xsl:value-of select="@category"/>
								</td>
								<td>
									<xsl:value-of select="ISBN"/>
								</td>
								<td>
									<xsl:value-of select="title"/>
								</td>
								<td><xsl:apply-templates select="author"></xsl:apply-templates></td>
								<td>
									<xsl:value-of select="press"/>
								</td>
								<td>
									<xsl:value-of select="price"/>
								</td>
							</tr>
						</xsl:when>
						<xsl:otherwise>
							<tr bgcolor="blue">
								<td>
									<xsl:value-of select="@id"/>
								</td>
								<td>
									<xsl:value-of select="@category"/>
								</td>
								<td>
									<xsl:value-of select="ISBN"/>
								</td>
								<td>
									<xsl:value-of select="title"/>
								</td>
								<td><xsl:apply-templates select="author"></xsl:apply-templates></td>
								<td>
									<xsl:value-of select="press"/>
								</td>
								<td>
									<xsl:value-of select="price"/>
								</td>
							</tr>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
			</table>
		</html>
	</xsl:template>
</xsl:stylesheet>
