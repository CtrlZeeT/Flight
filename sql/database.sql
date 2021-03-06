CREATE DATABASE [FLIGHT]
use [FLIGHT]

CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [varchar](50) primary key NOT NULL,
	[ThoiGianTao] [datetime] NULL,
	[TongTien] [money] NULL,
)

CREATE TABLE [dbo].[VeChuyenBay](
	[MaVe] [varchar](50) primary key NOT NULL,
	[GiaVe] [money] NULL,
	[HoTen] [nvarchar](50) NULL,
	[CMND] [varchar](50) NULL,
	[SDT] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
	[TrangThai] [nvarchar](50) NULL,
	[MaHoaDon] [varchar](50) foreign key references [dbo].[HoaDon]([MaHoaDon])NULL,
)

CREATE TABLE ThamSo (
	TenThamSo varchar(50) primary key,
	GiaTri int,
)

CREATE FUNCTION [dbo].[AUTO_IDHD]()
RETURNS VARCHAR(16)
AS
BEGIN
	DECLARE @id VARCHAR(16)
	IF (SELECT COUNT(MaHoaDon) FROM HoaDon) = 0
		SET @id = 0
	ELSE
		SELECT @id = MAX(RIGHT(MaHoaDon, 5)) FROM HoaDon
		SELECT @id = CASE
			WHEN @id = 99999 THEN CONVERT(VARCHAR,GETDATE(),112) + 'HD00001'
			WHEN @id >= 0 and @id < 9 THEN CONVERT(VARCHAR,GETDATE(),112) + 'HD0000' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 9 THEN CONVERT(VARCHAR,GETDATE(),112) + 'HD000' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 99 THEN CONVERT(VARCHAR,GETDATE(),112) + 'HD00' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 999 THEN CONVERT(VARCHAR,GETDATE(),112) + 'HD0' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 9999 THEN CONVERT(VARCHAR,GETDATE(),112) + 'HD' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
		END
	RETURN @id
END

CREATE FUNCTION [dbo].[AUTO_IDVCB]()
RETURNS VARCHAR(16)
AS
BEGIN
	DECLARE @id VARCHAR(16)
	IF (SELECT COUNT(MaVe) FROM VeChuyenBay) = 0
		SET @id = 0
	ELSE
		SELECT @id = MAX(RIGHT(MaVe, 5)) FROM VeChuyenBay
		SELECT @id = CASE
			WHEN @id = 99999 THEN CONVERT(VARCHAR,GETDATE(),112) + 'VCB00001'
			WHEN @id >= 0 and @id < 9 THEN CONVERT(VARCHAR,GETDATE(),112) + 'VCB0000' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 9 THEN CONVERT(VARCHAR,GETDATE(),112) + 'VCB000' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 99 THEN CONVERT(VARCHAR,GETDATE(),112) + 'VCB00' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 999 THEN CONVERT(VARCHAR,GETDATE(),112) + 'VCB0' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
			WHEN @id >= 9999 THEN CONVERT(VARCHAR,GETDATE(),112) + 'VCB' + CONVERT(CHAR, CONVERT(INT, @id) + 1)
		END
	RETURN @id
END

ALTER TABLE [dbo].[HoaDon] ADD  CONSTRAINT [IDHD]  DEFAULT ([dbo].[AUTO_IDHD]()) FOR [MaHoaDon]

ALTER TABLE [dbo].[VeChuyenBay] ADD  CONSTRAINT [IDVCB]  DEFAULT ([dbo].[AUTO_IDVCB]()) FOR [MaVe]


